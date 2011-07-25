package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

final class Stream implements IDeliveryCallback {

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
		transport.initialize(td.parameters(), this);
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
		transport.send(m.toString(), m.id().toString());
	}

	@Override
	public void accepted(String id) {
	}

	@Override
	public void wontAccept(String id, String reason) {
	}

	@Override
	public void retry(String id, String reason) {
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
