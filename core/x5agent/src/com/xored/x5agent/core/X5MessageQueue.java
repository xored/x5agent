package com.xored.x5agent.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.xored.x5.DeliveryStatus;
import com.xored.x5.X5Fact;
import com.xored.x5.X5FactResponse;
import com.xored.x5.X5Response;
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
								} catch (IOException e) {
									X5Agent.Instance.logError(e);
									retry = true;
								} catch (Exception e) {
									X5Agent.Instance.logError(e);
									retry = false;
									X5Agent.Instance.logInfo("Not accepted: "
											+ fact.getId());
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
		private final AtomicBoolean disposed = new AtomicBoolean(false);
		private X5Transport transport;

		private Transport(X5TransportDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		private X5Transport getTransport() {
			if (transport == null && !disposed.get()) {
				synchronized (this) {
					if (transport == null && !disposed.get()) {
						try {
							transport = descriptor.create();
							X5Agent.Instance.logInfo("Transport created");
							if (transport == null)
								throw new NullPointerException(
										"Transport is not specified");
							transport.initialize(descriptor.parameters());
							X5Agent.Instance.logInfo("Transport initialized");
						} catch (IOException e) {
							X5Agent.Instance.logError(e);
							if (transport != null) {
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
						} catch (Exception e) {
							X5Agent.Instance.logError(e);
							dispose();
						}
					}

				}
			}
			return transport;
		}

		private X5Response send(X5Fact message) throws IOException {
			if (!disposed.get()) {
				X5Transport t = getTransport();
				if (t != null) {
					X5Agent.Instance
							.logInfo("Send message: " + message.getId());
					X5Response res = t.send(message);
					X5Agent.Instance
							.logInfo("Got response: " + message.getId());
					return res;
				}
			}
			return null;
		}

		private boolean isDisposed() {
			return disposed.get();
		}

		private synchronized void dispose() {
			if (disposed.compareAndSet(false, true)) {
				X5Agent.Instance.logInfo("Dispose transport support");
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
