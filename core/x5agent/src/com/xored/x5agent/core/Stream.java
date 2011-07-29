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
		X5Agent.Instance.getLog().info(m.getId() + " generated");
		queue.add(m);
	}

	private final class Event implements EventListener {

		private final EventDescriptor descriptor;
		private EventProvider provider;
		private List<SnapshotProvider> snapshots = new ArrayList<SnapshotProvider>();

		public Event(EventDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		public void initialize() {
			for (SnapshotDescriptor sd : descriptor.snapshots()) {
				SnapshotProvider sp = sd.create();
				sp.initialize(sd.parameters());
				snapshots.add(sp);
			}
			provider = descriptor.create();
			provider.initialize(descriptor.parameters());
			provider.addListener(this);
		}

		public void dispose() {
			provider.removeListener(this);
			for (SnapshotProvider s : snapshots) {
				s.dispose();
			}
			provider.dispose();
		}

		@Override
		public void handle(Object e) {
			Map<String, UUID> references = new HashMap<String, UUID>();
			for (SnapshotProvider sp : snapshots) {
				Message m = Message.create(sp.getType(), sp.getSnapshot());
				references.put(sp.getType(), m.getUuid());
				Stream.this.handle(m);
			}
			Message m = Message.create(provider.getType(), e, references);
			Stream.this.handle(m);
		}

	}
}
