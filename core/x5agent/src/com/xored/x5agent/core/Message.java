package com.xored.x5agent.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

final class Message {

	public static Message create(String type, Object target, UUID[] references) {
		return new Message(type, target, references);
	}

	public static Message create(String type, Object target) {
		return new Message(type, target, null);
	}

	private static final String ID_ATTR = "id";
	private static final String CLIENT_ATTR = "client";
	private static final String CLIENT_APP_ATTR = "client-app";
	private static final String GENERATED_ATTR = "generated";
	private static final String TYPE_ATTR = "type";
	private static final String REFS_ATTR = "refs";

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private final UUID id;
	private final long timestamp;
	private final String type;
	private final UUID[] references;
	private final Object target;

	public Message(String type, Object target, UUID[] references) {
		this.id = UUID.randomUUID();
		this.timestamp = System.currentTimeMillis();
		this.type = type;
		this.target = target;
		this.references = references;
	}

	public UUID id() {
		return id;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		JsonObject o = (JsonObject) gson.toJsonTree(target);
		o.addProperty(ID_ATTR, id.toString());
		o.addProperty(CLIENT_ATTR, X5Agent.Instance.getClientUUID().toString());
		o.addProperty(CLIENT_APP_ATTR, X5Agent.Instance.getClientApp());
		o.addProperty(GENERATED_ATTR, dateFormat.format(timestamp));
		o.addProperty(TYPE_ATTR, type);
		if (references != null && references.length > 0)
			o.add(REFS_ATTR, gson.toJsonTree(references));
		return o.toString();
	}

}
