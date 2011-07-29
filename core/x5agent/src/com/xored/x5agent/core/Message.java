package com.xored.x5agent.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public final class Message {

	public static Message create(String type, Object event,
			Map<String, UUID> references) {
		return new Message(type, event, references);
	}

	public static Message create(String type, Object event) {
		return new Message(type, event, null);
	}

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

	private final UUID id;
	private final long timestamp;
	private final String type;
	private final Map<String, UUID> references;
	private final Object event;

	Message(String type, Object event, Map<String, UUID> references) {
		this.id = UUID.randomUUID();
		this.timestamp = System.currentTimeMillis();
		this.type = type;
		this.event = event;
		this.references = references;
	}

	public String getId() {
		return id.toString();
	}

	UUID getUuid() {
		return id;
	}

	public String getBody() {
		Gson gson = new Gson();
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("id", getId());
		message.put("clientapp", X5Agent.Instance.getClientUUID().toString());
		message.put("client", X5Agent.Instance.getClientApp());
		message.put("generated", dateFormat.format(timestamp));
		message.put("schema", type);
		if (event instanceof JsonObject) {
			message.put("body", event);
		}
		if (references != null && !references.isEmpty()) {
			message.put("refs", gson.toJsonTree(references));
		}
		String body = gson.toJson(message);
		X5Agent.Instance.getLog().info(body);
		return body;
	}

	@Override
	public String toString() {
		return getBody();
	}

}
