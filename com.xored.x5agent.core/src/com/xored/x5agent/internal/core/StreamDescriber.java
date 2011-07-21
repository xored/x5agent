package com.xored.x5agent.internal.core;

import org.eclipse.core.runtime.Assert;

import com.xored.x5agent.core.IMessage;

public class StreamDescriber implements IMessageListener {

	private final EventDescriber[] eventDescribers;
	private final TransportDescriber[] transportDescribers;

	public StreamDescriber(EventDescriber[] eventDescribers,
			TransportDescriber[] transportDescribers) {
		Assert.isNotNull(eventDescribers);
		Assert.isNotNull(transportDescribers);
		this.eventDescribers = eventDescribers;
		this.transportDescribers = transportDescribers;
	}

	public void initialize() {
		for (TransportDescriber t : transportDescribers) {
			t.initialize();
		}
		for (EventDescriber e : eventDescribers) {
			e.initialize();
			e.addListener(this);
		}
	}

	public void dispose() {
		for (EventDescriber e : eventDescribers) {
			e.dispose();
			e.removeListener(this);
		}
		for (TransportDescriber t : transportDescribers) {
			t.dispose();
		}
	}

	@Override
	public void handle(IMessage e) {
		for (TransportDescriber t : transportDescribers) {
			t.send(e);
		}
	}

}
