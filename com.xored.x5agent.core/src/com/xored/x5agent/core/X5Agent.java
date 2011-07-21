package com.xored.x5agent.core;

import java.util.UUID;

import com.xored.x5agent.internal.core.Message;
import com.xored.x5agent.internal.core.StreamDescriber;
import com.xored.x5agent.internal.core.X5AgentRegistry;

public enum X5Agent {
	Instance;

	public void initialize() {
		for (StreamDescriber s : X5AgentRegistry.Instance.getStreams()) {
			s.initialize();
		}
	}

	public void dispose() {
		for (StreamDescriber s : X5AgentRegistry.Instance.getStreams()) {
			s.dispose();
		}
	}

	public IMessage createMessage(String type, IEvent e, UUID[] references) {
		return new Message(type, references);
	}

	public IMessage createMessage(String type, Object o) {
		return new Message(type, null);
	}
}
