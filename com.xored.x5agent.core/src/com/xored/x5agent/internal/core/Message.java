package com.xored.x5agent.internal.core;

import java.util.UUID;

import com.xored.x5agent.core.IMessage;

public class Message implements IMessage {

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

	@Override
	public UUID id() {
		return id;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public UUID[] getReferences() {
		return references;
	}

}
