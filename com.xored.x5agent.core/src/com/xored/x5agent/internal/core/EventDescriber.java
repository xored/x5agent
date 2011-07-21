package com.xored.x5agent.internal.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.Assert;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.IEvent;
import com.xored.x5agent.core.IEventListener;
import com.xored.x5agent.core.IEventProvider;
import com.xored.x5agent.core.IMessage;
import com.xored.x5agent.core.X5Agent;

public class EventDescriber implements IEventListener {

	private final String providerId;
	private IEventProvider provider;
	private final IConfiguration configuration;
	private final SnapshotDescriber[] snapshotDescribers;

	private final List<IMessageListener> listeners = new ArrayList<IMessageListener>();

	public EventDescriber(String providerId, IConfiguration configuration,
			SnapshotDescriber[] snapshotDescribers) {
		Assert.isNotNull(providerId);
		Assert.isNotNull(configuration);
		Assert.isNotNull(snapshotDescribers);
		this.providerId = providerId;
		this.configuration = configuration;
		this.snapshotDescribers = snapshotDescribers;
	}

	public String id() {
		return providerId;
	}

	public void initialize() {
		provider = X5AgentRegistry.Instance.createEventProvider(providerId);
		provider.initialize(configuration);
		provider.addListener(this);
		for (SnapshotDescriber s : snapshotDescribers) {
			s.initialize();
		}
	}

	public void dispose() {
		provider.dispose();
		provider.removeListener(this);
	}

	public void addListener(IMessageListener listener) {
		synchronized (this) {
			listeners.add(listener);
		}
	}

	public void removeListener(IMessageListener listener) {
		synchronized (this) {
			listeners.remove(listener);
		}
	}

	@Override
	public void handle(IEvent e) {
		UUID references[] = new UUID[snapshotDescribers.length];
		for (int i = 0; i < snapshotDescribers.length; i++) {
			IMessage m = X5Agent.Instance.createMessage(
					snapshotDescribers[i].id(),
					snapshotDescribers[i].getSnapshot());
			notifyListeners(m);
			references[i] = m.id();
		}
		IMessage m = X5Agent.Instance.createMessage(id(), e, references);
		notifyListeners(m);
	}

	private void notifyListeners(IMessage m) {
		synchronized (this) {
			for (IMessageListener l : listeners) {
				l.handle(m);
			}
		}
	}

}
