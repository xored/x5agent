package com.xored.x5agent.core;

import org.eclipse.core.runtime.IStatus;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.xored.x5agent.core.AbstractX5EventProvider;

public abstract class StatusEventProvider extends AbstractX5EventProvider {

	@Override
	public String getSchema() {
		return "sherlock.eclipse_status";
	}

	protected JsonElement toJson(IStatus status) {
		JsonObject o = new JsonObject();
		o.addProperty("message", status.getMessage());
		o.addProperty("code", status.getCode());
		o.addProperty("severity", status.getSeverity());
		// TODO o.addProperty("featureGuess", null);
		o.addProperty("plugin", status.getPlugin());
		// TODO o.addProperty("pluginVersion", null);
		// TODO o.addProperty("thread", null);
		// TODO o.addProperty("children", null);
		Throwable e = status.getException();
		if (e != null)
			o.add("exception", toJsonTree(e));
		return o;
	}

	protected JsonElement toJsonTree(Throwable t) {
		JsonObject o = new JsonObject();
		o.addProperty("message", t.getMessage());
		o.addProperty("class", t.getClass().getName());
		Throwable cause = t.getCause();
		if (cause != null && cause != t)
			o.add("cause", toJsonTree(cause));
		JsonArray stacktrace = new JsonArray();
		for (StackTraceElement e : t.getStackTrace()) {
			stacktrace.add(toJsonTree(e));
		}
		o.add("stacktrace", stacktrace);
		return o;
	}

	protected JsonElement toJsonTree(StackTraceElement e) {
		JsonArray a = new JsonArray();
		a.add(new JsonPrimitive(e.getFileName()));
		a.add(new JsonPrimitive(e.getClassName()));
		a.add(new JsonPrimitive(e.getMethodName()));
		a.add(new JsonPrimitive(e.getLineNumber()));
		if (e.isNativeMethod())
			a.add(new JsonPrimitive(true));
		return a;
	}

}
