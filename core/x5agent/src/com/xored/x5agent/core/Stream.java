package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.xored.x5agent.core.StreamDescriptor.EventDescriptor;
import com.xored.x5agent.core.StreamDescriptor.SnapshotDescriptor;

public final class Stream {

	private final MessageQueue queue;
	private final List<Event> events = new ArrayList<Event>();
	private final Map<String, Message> snapshots = new HashMap<String, Message>();

	public static Stream create(StreamDescriptor descriptor) {
		return new Stream(descriptor);
	}

	public static StreamBuilder build() {
		throw new UnsupportedOperationException();
	}

	Stream(StreamDescriptor descriptor) {
		for (EventDescriptor ed : descriptor.events()) {
			this.events.add(new Event(ed));
		}
		this.queue = new MessageQueue(descriptor.transport());
	}

	void initialize() {
		queue.initialize();
		for (Event e : events) {
			e.initialize();
		}
	}

	void dispose() {
		for (Event e : events) {
			e.dispose();
		}
		queue.dispose();
	}

	private void handle(Message m) {
		queue.add(m);
	}

	private final class Event implements EventListener {

		private final EventDescriptor descriptor;
		private EventProvider provider;
		private List<SnapshotProvider> snapshotProviders = new ArrayList<SnapshotProvider>();

		Event(EventDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		void initialize() {
			for (SnapshotDescriptor sd : descriptor.snapshots()) {
				SnapshotProvider sp = sd.create();
				sp.initialize(sd.parameters());
				snapshotProviders.add(sp);
			}
			provider = descriptor.create();
			provider.initialize(descriptor.parameters());
			provider.addListener(this);
		}

		void dispose() {
			provider.removeListener(this);
			for (SnapshotProvider s : snapshotProviders) {
				s.dispose();
			}
			provider.dispose();
		}

		@Override
		public void handle(Object e) {
			if (e == null) {
				return;
			}
			Map<String, UUID> references = new HashMap<String, UUID>();
			for (SnapshotProvider sp : snapshotProviders) {
				Object s = sp.getSnapshot();
				if (s == null) {
					continue;
				}
				Message m = snapshots.get(sp.getType());
				if (m != null) {
					if (!s.equals(m.getObject())) {
						m = null;
					}
				}
				if (m == null) {
					m = Message.create(sp.getType(), s);
					Stream.this.handle(m);
					snapshots.put(sp.getType(), m);
				}
				references.put(sp.getType(), m.getUuid());
			}
			Message m = Message.create(provider.getType(), e, references);
			Stream.this.handle(m);
		}

	}
}
