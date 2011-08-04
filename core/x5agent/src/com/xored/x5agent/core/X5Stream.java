package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import com.xored.x5agent.core.X5StreamDescriptor.X5EventDescriptor;
import com.xored.x5agent.core.X5StreamDescriptor.X5SnapshotDescriptor;
import com.xored.x5agent.model.X5Fact;
import com.xored.x5agent.model.X5Factory;

public final class X5Stream {

	private final X5MessageQueue queue;
	private final List<Event> events = new ArrayList<Event>();
	private final Map<String, X5Fact> snapshots = new HashMap<String, X5Fact>();

	public static X5Stream create(X5StreamDescriptor descriptor) {
		return new X5Stream(descriptor);
	}

	public static X5StreamBuilder build() {
		throw new UnsupportedOperationException();
	}

	X5Stream(X5StreamDescriptor descriptor) {
		for (X5EventDescriptor ed : descriptor.events()) {
			this.events.add(new Event(ed));
		}
		this.queue = new X5MessageQueue(descriptor.transport());
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

	private void handle(X5Fact m) {
		queue.add(m);
	}

	private final class Event implements X5EventListener {

		private final X5EventDescriptor descriptor;
		private X5EventProvider provider;
		private List<X5SnapshotProvider> snapshotProviders = new ArrayList<X5SnapshotProvider>();

		Event(X5EventDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		void initialize() {
			for (X5SnapshotDescriptor sd : descriptor.snapshots()) {
				X5SnapshotProvider sp = sd.create();
				sp.initialize(sd.parameters());
				snapshotProviders.add(sp);
			}
			provider = descriptor.create();
			provider.initialize(descriptor.parameters());
			provider.addListener(this);
		}

		void dispose() {
			provider.removeListener(this);
			for (X5SnapshotProvider s : snapshotProviders) {
				s.dispose();
			}
			provider.dispose();
		}

		@Override
		public void notify(EObject e) {
			if (e == null) {
				return;
			}
			Map<String, String> references = new HashMap<String, String>();
			for (X5SnapshotProvider sp : snapshotProviders) {
				EObject s = sp.getSnapshot();
				if (s == null) {
					continue;
				}
				X5Fact m = snapshots.get(sp.getSchema());
				if (m != null) {
					if (!s.equals(m.getBody())) {
						m = null;
					}
				}
				if (m == null) {
					m = create(sp.getSchema(), s);
					X5Stream.this.handle(m);
					snapshots.put(sp.getSchema(), m);
				}
				references.put(sp.getSchema(), m.getId());
			}
			X5Fact m = create(provider.getSchema(), e, references);
			X5Stream.this.handle(m);
		}

		private X5Fact create(String schema, EObject fact,
				Map<String, String> references) {
			X5Fact message = X5Factory.eINSTANCE.createX5Fact();
			message.setId(UUID.randomUUID().toString());
			message.setClient(X5Agent.Instance.getClient());
			message.setClientapp(X5Agent.Instance.getClientApp());
			message.setBody(fact);
			message.setTimestamp(System.currentTimeMillis());
			message.setSchema(schema);
			message.getReferences().putAll(references);
			return message;
		}

		private X5Fact create(String schema, EObject fact) {
			Map<String, String> refs = Collections.emptyMap();
			return create(schema, fact, refs);
		}

	}
}
