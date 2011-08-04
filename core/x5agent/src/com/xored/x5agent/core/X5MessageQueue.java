package com.xored.x5agent.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.xored.x5agent.core.X5StreamDescriptor.X5TransportDescriptor;
import com.xored.x5agent.model.DeliveryStatus;
import com.xored.x5agent.model.X5Fact;
import com.xored.x5agent.model.X5FactResponse;
import com.xored.x5agent.model.X5Response;

class X5MessageQueue {

	// TODO store fact queue in file system

	private final KeepAliveTransport transport;
	private final Set<X5Fact> facts = new HashSet<X5Fact>();
	private final ExecutorService pool = Executors.newFixedThreadPool(3);
	private final AtomicBoolean disposed = new AtomicBoolean(false);

	X5MessageQueue(X5TransportDescriptor descriptor) {
		this.transport = new KeepAliveTransport(descriptor);
	}

	long getSendInterval() {
		return 5000l;
	}

	void initialize() {
		pool.execute(new Sender());
	}

	void add(X5Fact fact) {
		X5Agent.Instance.getLog().info(fact.getId() + " put to queue");
		facts.add(fact);
	}

	void dispose() {
		disposed.set(true);
		transport.dispose();
	}

	private final class Sender implements Runnable {
		@Override
		public void run() {
			try {
				while (!disposed.get()) {
					Thread.sleep(getSendInterval());
					X5Fact[] array;
					synchronized (facts) {
						array = facts.toArray(new X5Fact[facts.size()]);
						facts.clear();
					}
					for (final X5Fact fact : array) {
						pool.execute(new Runnable() {
							@Override
							public void run() {
								X5Response response = transport.send(fact);
								if (response == null) {
									synchronized (facts) {
										X5Agent.Instance.getLog()
												.info(fact.getId()
														+ " back to queue");
										facts.add(fact);
									}
								}
								X5FactResponse factResponse = (X5FactResponse) response;
								if (factResponse.getStatus() == DeliveryStatus.RETRY) {
									synchronized (facts) {
										X5Agent.Instance.getLog()
												.info(fact.getId()
														+ " back to queue");
										facts.add(fact);
									}
								}
							}
						});
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				// TODO handle
			}
		}
	}

	final class KeepAliveTransport {

		private final X5TransportDescriptor descriptor;
		private X5Transport transport;

		private KeepAliveTransport(X5TransportDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		private synchronized X5Response send(X5Fact message) {
			try {
				if (transport == null) {
					transport = descriptor.create();
					transport.initialize(descriptor.parameters());
				}
				return transport.send(message);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO handle exception
				try {
					if (transport != null) {
						transport.dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					// TODO handle exception
				}
				transport = null;
			}
			return null;
		}

		private void dispose() {
			if (transport != null) {
				try {
					transport.dispose();
				} catch (Exception e) {
					// TODO handle exception
				}
				transport = null;
			}
		}
	}
}
