package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum X5Agent {
	Instance;

	private final List<Stream> streams = new ArrayList<Stream>();

	public void initialize() {
	}

	public void run(IStreamDescriber describer) {
		Stream s = new Stream(describer);
		streams.add(s);
		s.initialize();
	}

	public void dispose() {
		for (Stream s : streams) {
			s.dispose();
		}
	}

	private static final class Stream {

		private final IStreamDescriber describer;
		private List<Event> events = new ArrayList<Event>();
		private ITransport transport;

		public Stream(IStreamDescriber describer) {
			for (IEventDescriber ed : describer.events()) {
				events.add(new Event(ed));
			}
			this.describer = describer;
		}

		public void initialize() {
			ITransportDescriber td = describer.transport();
			transport = td.create();
			transport.initialize(td.parameters());
			for (Event e : events) {
				e.initialize();
			}
		}

		public void dispose() {
			for (Event e : events) {
				e.dispose();
			}
			transport.dispose();
		}

		private void handle(Message m) {
			transport.send(m.toString());
		}

		private final class Event implements IEventListener {

			private final IEventDescriber describer;
			private IEventProvider provider;
			private List<ISnapshotProvider> snapshots = new ArrayList<ISnapshotProvider>();

			public Event(IEventDescriber describer) {
				this.describer = describer;
			}

			public void initialize() {
				for (ISnapshotDescriber sd : describer.snapshots()) {
					ISnapshotProvider sp = sd.create();
					sp.initialize(sd.parameters());
					snapshots.add(sp);
				}
				provider = describer.create();
				provider.initialize(describer.parameters());
				provider.addListener(this);
			}

			public void dispose() {
				provider.removeListener(this);
				for (ISnapshotProvider s : snapshots) {
					s.dispose();
				}
				provider.dispose();
			}

			@Override
			public void handle(Object e) {
				List<UUID> references = new ArrayList<UUID>(snapshots.size());
				for (ISnapshotProvider sp : snapshots) {
					Message m = Message.create(sp.type(), sp.getSnapshot());
					references.add(m.id());
					Stream.this.handle(m);
				}
				Message m = Message.create(provider.type(), e,
						references.toArray(new UUID[references.size()]));
				Stream.this.handle(m);
			}

		}

	}

	@SuppressWarnings("unused")
	private static final class Message {

		public static Message create(String type, Object e, UUID[] references) {
			return new Message(type, references);
		}

		public static Message create(String type, Object o) {
			return new Message(type, null);
		}

		private final UUID id;
		private final long timestamp;
		private final String type;
		private final UUID[] references;

		public Message(String type, UUID[] references) {
			id = UUID.randomUUID();
			timestamp = System.currentTimeMillis();
			this.type = type;
			this.references = references;
		}

		public UUID id() {
			return id;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public String getType() {
			return type;
		}

		public UUID[] getReferences() {
			return references;
		}

	}

	@SuppressWarnings("unused")
	private static enum JsonConverter {
		Instance;
		public String convert(Object o) {
			// TODO Auto-generated method stub
			return o.toString();
		}
	}

}
