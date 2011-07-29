package com.xored.x5agent.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import com.xored.x5agent.core.StreamDescriptor.TransportDescriptor;

class MessageQueue {

	private final KeepAliveTransport transport;
	private final DeliveryCallback callback;
	private final Map<UUID, Message> toSend = new HashMap<UUID, Message>();
	private final Map<UUID, Message> sending = new HashMap<UUID, Message>();
	private final Sender sender = new Sender();
	private final AtomicBoolean disposed = new AtomicBoolean(false);

	MessageQueue(TransportDescriptor descriptor) {
		this.transport = new KeepAliveTransport(descriptor);
		this.callback = new DeliveryCallbackImpl();
	}

	private long getSendInterval() {
		// TODO make configurable
		return 5000l;
	}

	private long getDeliveryTimeout() {
		// TODO make configurable
		return 20000l;
	}

	void initialize() {
		new Thread(sender).start();
	}

	void add(Message message) {
		X5Agent.Instance.getLog().info(message.getId() + " put toSend");
		toSend.put(message.getUuid(), message);
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
					if (!toSend.isEmpty()) {
						Set<UUID> uuids = toSend.keySet();
						Collection<Message> values = toSend.values();
						Message[] array = values.toArray(new Message[values
								.size()]);
						if (transport.send(array)) {
							sending.putAll(toSend);
							toSend.clear();
							new Thread(new MessageTracker(uuids)).start();
						}
						for (Message m : array) {
							X5Agent.Instance.getLog().info(m.getId() + " send");
						}
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				// TODO handle
			}
		}
	}

	private final class MessageTracker implements Runnable {
		final Set<UUID> ids;

		MessageTracker(Set<UUID> ids) {
			this.ids = ids;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(getDeliveryTimeout());
				for (UUID id : ids) {
					Message removed = sending.remove(id);
					if (removed != null) {
						X5Agent.Instance.getLog().info(id + " timeout");
						toSend.put(id, removed);
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private final class DeliveryCallbackImpl implements DeliveryCallback {

		@Override
		public void accepted(String id) {
			try {
				UUID uuid = UUID.fromString(id);
				Message removed = sending.remove(uuid);
				X5Agent.Instance.getLog().info(id + " dropped");
				if (removed == null) {
					// TODO handle this case
				}
			} catch (Exception e) {
				// TODO handle invalid id
			}
		}

		@Override
		public void wontAccept(String id, String reason) {
			// TODO proper handling
			accepted(id);
		}

		@Override
		public void retry(String id, String reason) {
			try {
				UUID uuid = UUID.fromString(id);
				Message removed = sending.remove(uuid);
				if (removed != null) {
					X5Agent.Instance.getLog().info(id + " failed");
					toSend.put(uuid, removed);
				}
			} catch (Exception e) {
				// TODO handle invalid id
			}
		}

	}

	final class KeepAliveTransport {

		private final TransportDescriptor descriptor;
		private Transport transport;

		private KeepAliveTransport(TransportDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		private boolean send(Message[] messages) {
			try {
				if (transport == null) {
					transport = descriptor.create();
					transport.initialize(descriptor.parameters(), callback);
				}
				transport.send(messages);
				return true;
			} catch (Exception e) {
				// TODO handle exception
				try {
					transport.dispose();
				} catch (Exception e1) {
					// TODO handle exception
				}
				transport = null;
			}
			return false;
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
