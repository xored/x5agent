package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.xored.x5.DeliveryStatus;
import com.xored.x5.X5Fact;
import com.xored.x5.X5FactResponse;
import com.xored.x5.X5Response;
import com.xored.x5.X5TransportFatalException;
import com.xored.x5agent.core.X5StreamDescriptor.X5TransportDescriptor;

class X5MessageQueue {

	// TODO store fact queue in file system

	private final Transport transport;
	private final List<X5Fact> facts = new ArrayList<X5Fact>();
	private final ExecutorService pool = Executors.newFixedThreadPool(3);
	private boolean disposed = false;

	X5MessageQueue(X5TransportDescriptor descriptor) {
		this.transport = new Transport(descriptor);
	}

	long getSendInterval() {
		return 5000l;
	}

	void initialize() {
		pool.execute(new Sender());
	}

	void add(X5Fact fact) {
		X5Agent.Instance.logInfo("Fact was added to queue: " + fact.getId());
		synchronized (this) {
			if (!disposed) {
				facts.add(fact);
			}
		}
	}

	private synchronized X5Fact[] getFacts() {
		if (!disposed) {
			X5Fact[] array = facts.toArray(new X5Fact[facts.size()]);
			facts.clear();
			return array;
		}
		return new X5Fact[0];
	}

	synchronized void dispose() {
		if (!disposed) {
			disposed = true;
			transport.dispose();
		}
	}

	private final class Sender implements Runnable {
		@Override
		public void run() {
			try {
				X5Agent.Instance.logInfo("Message sender started");
				while (true) {
					Thread.sleep(getSendInterval());
					if (transport.isDisposed()) {
						X5Agent.Instance.logInfo("Message sender stopped");
						return;
					}
					X5Fact[] array = getFacts();
					for (final X5Fact fact : array) {
						pool.execute(new Runnable() {
							@Override
							public void run() {
								boolean retry = true;
								try {
									X5Response response = transport.send(fact);
									retry = response == null
											|| (response instanceof X5FactResponse && ((X5FactResponse) response)
													.getStatus() == DeliveryStatus.RETRY);
								} catch (Exception e) {
									X5Agent.Instance.logError(e);
									retry = true;
								}
								if (retry && !transport.isDisposed()) {
									add(fact);
								}
							}
						});
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				X5Agent.Instance.logError(e);
			}
		}
	}

	private final class Transport {

		private final X5TransportDescriptor descriptor;
		private boolean disposed = false;
		private X5Transport transport;

		private Transport(X5TransportDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		private synchronized X5Response send(X5Fact message) {
			if (!disposed) {
				boolean initialized = transport != null;
				try {
					if (transport == null) {
						X5Agent.Instance
								.logInfo("Create new transport instance");
						transport = descriptor.create();
						if (transport == null)
							throw new X5TransportFatalException(
									"Transport is not specified");
						X5Agent.Instance.logInfo("Init created transport");
						transport.initialize(descriptor.parameters());
						initialized = true;
					}
					X5Agent.Instance
							.logInfo("Send message: " + message.getId());
					return transport.send(message);
				} catch (X5TransportFatalException e) {
					X5Agent.Instance.logError(e);
					dispose();
				} catch (Exception e) {
					X5Agent.Instance.logError(e);
					if (transport != null && !initialized) {
						try {
							X5Agent.Instance
									.logInfo("Dispose broken transport");
							transport.dispose();
						} catch (Exception e1) {
							X5Agent.Instance.logError(e1);
						} finally {
							transport = null;
						}
					}
				}
			}
			return null;
		}

		private synchronized boolean isDisposed() {
			return disposed;
		}

		private synchronized void dispose() {
			if (!disposed) {
				X5Agent.Instance.logInfo("Dispose transport support");
				disposed = true;
				if (transport != null) {
					try {
						transport.dispose();
					} catch (Exception e) {
						X5Agent.Instance.logError(e);
					}
					transport = null;
				}
			}
		}
	}
}
