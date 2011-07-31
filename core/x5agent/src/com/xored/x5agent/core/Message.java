package com.xored.x5agent.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class Message {

	public static Message create(String type, Object object,
			Map<String, UUID> references) {
		return new Message(type, object, references);
	}

	public static Message create(String type, Object object) {
		return new Message(type, object, null);
	}

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

	private final UUID id;
	private final long timestamp;
	private final String type;
	private final Map<String, UUID> references;
	private final Object object;

	Message(String type, Object object, Map<String, UUID> references) {
		this.id = UUID.randomUUID();
		this.timestamp = System.currentTimeMillis();
		this.type = type;
		this.object = object;
		this.references = references;
	}

	public String getId() {
		return id.toString();
	}

	UUID getUuid() {
		return id;
	}

	Object getObject() {
		return object;
	}

	public String getBody() {
		Gson gson = new Gson();
		JsonObject message = new JsonObject();
		// Map<String, Object> message = new HashMap<String, Object>();
		message.addProperty("id", getId());
		message.addProperty("clientapp", X5Agent.Instance.getClientUUID()
				.toString());
		message.addProperty("client", X5Agent.Instance.getClientApp());
		message.addProperty("generated", dateFormat.format(timestamp));
		message.addProperty("schema", type);
		if (object instanceof JsonObject) {
			message.add("body", (JsonElement) object);
		}
		if (references != null && !references.isEmpty()) {
			message.add("refs", gson.toJsonTree(references));
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
