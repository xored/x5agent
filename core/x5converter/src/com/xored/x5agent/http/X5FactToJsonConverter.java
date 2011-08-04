package com.xored.x5agent.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.xored.emfjson.Emf2Json;
import com.xored.sherlock.core.model.sherlock.EclipseFeature;
import com.xored.sherlock.core.model.sherlock.EclipseInfo;
import com.xored.sherlock.core.model.sherlock.EclipsePlugin;
import com.xored.sherlock.core.model.sherlock.EclipsePreference;
import com.xored.sherlock.core.model.sherlock.EclipseStatus;
import com.xored.sherlock.core.model.sherlock.JavaException;
import com.xored.sherlock.core.model.sherlock.JavaInfo;
import com.xored.sherlock.core.model.sherlock.JavaStackTraceEntry;
import com.xored.sherlock.core.model.sherlock.SherlockPackage;
import com.xored.sherlock.core.model.sherlock.SystemInfo;
import com.xored.x5agent.model.X5Fact;
import com.xored.x5agent.model.X5Package;

public class X5FactToJsonConverter {
	private final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

	public String convert(String json) {
		Emf2Json converter = new Emf2Json(X5Package.eINSTANCE,
				SherlockPackage.eINSTANCE);
		JsonParser p = new JsonParser();
		JsonElement e = p.parse(json);
		if (e instanceof JsonObject) {
			EObject eObject = converter.deserialize((JsonObject) e);
			if (eObject instanceof X5Fact) {
				return toJson((X5Fact) eObject).toString();
			}
		}
		throw new IllegalArgumentException("unexpected format");
	}

	private JsonObject toJson(X5Fact fact) {
		JsonObject o = new JsonObject();
		o.addProperty("id", fact.getId());
		o.addProperty("clientapp", fact.getClientapp());
		o.addProperty("client", fact.getClient());
		o.addProperty("generated", dateFormat.format(fact.getTimestamp()));
		EObject body = fact.getBody();
		if (body instanceof EclipseInfo) {
			o.add("body", toJson((EclipseInfo) body));
			o.addProperty("schema", "sherlok.eclipse_info");
		} else if (body instanceof JavaInfo) {
			o.add("body", toJson((JavaInfo) body));
			o.addProperty("schema", fact.getSchema());
		} else if (body instanceof SystemInfo) {
			o.add("body", toJson((SystemInfo) body));
			o.addProperty("schema", fact.getSchema());
		} else if (body instanceof EclipseStatus) {
			o.add("body", toJson((EclipseStatus) body));
			o.addProperty("schema", "sherlok.eclipse_status");
		}
		o.add("refs", toJson(fact.getReferences()));
		return o;
	}

	private JsonObject toJson(EclipseInfo info) {
		JsonObject o = new JsonObject();
		o.addProperty("productId", info.getProductId());
		o.addProperty("buildId", info.getBuildId());
		o.addProperty("applicationId", info.getApplicationId());
		o.add("applicationArgs", toJson(info.getApplicationArgs()));
		o.addProperty("workspaceLocation", info.getWorkspaceLocation());
		o.addProperty("workspacePartitionFreeDiskspace",
				info.getWorkspacePartitionFreeDiskspace());
		o.addProperty("workspacePartitionTotalDiskspace",
				info.getWorkspacePartitionTotalDiskspace());
		o.addProperty("workspacePartitionUsableDiskspace",
				info.getWorkspacePartitionUsableDiskspace());
		o.addProperty("uptime", info.getUptime());
		JsonArray plugins = new JsonArray();
		for (EclipsePlugin p : info.getPlugins()) {
			plugins.add(toJson(p));
		}
		o.add("plugins", plugins);
		JsonArray features = new JsonArray();
		for (EclipseFeature f : info.getFeatures()) {
			features.add(toJson(f));
		}
		o.add("features", features);
		JsonArray preferences = new JsonArray();
		for (EclipsePreference p : info.getPreferences()) {
			preferences.add(toJson(p));
		}
		o.add("preferences", preferences);
		return o;
	}

	private JsonElement toJson(EclipsePlugin plugin) {
		JsonObject o = new JsonObject();
		o.addProperty("name", plugin.getName());
		o.addProperty("provider", plugin.getProvider());
		o.addProperty("id", plugin.getId());
		o.addProperty("version", plugin.getVersion());
		return o;
	}

	private JsonElement toJson(EclipseFeature feature) {
		JsonObject o = new JsonObject();
		o.addProperty("name", feature.getName());
		o.addProperty("provider", feature.getProvider());
		o.addProperty("id", feature.getId());
		o.addProperty("version", feature.getVersion());
		return o;
	}

	private JsonElement toJson(EclipsePreference p) {
		JsonObject o = new JsonObject();
		o.addProperty("name", p.getName());
		o.addProperty("value", p.getValue());
		o.addProperty("path", p.getPath());
		return o;
	}

	private JsonObject toJson(JavaInfo info) {
		JsonObject o = new JsonObject();
		o.addProperty("runtimeVersion", info.getRuntimeVersion());
		o.addProperty("runtimeName", info.getRuntimeName());
		o.addProperty("totalMemory", info.getTotalMemory());
		o.addProperty("maxMemory", info.getMaxMemory());
		o.addProperty("freeMemory", info.getFreeMemory());
		o.add("properties", toJson(info.getProperties()));
		o.add("args", toJson(info.getArgs()));
		return o;
	}

	private JsonObject toJson(SystemInfo info) {
		JsonObject o = new JsonObject();
		o.addProperty("osName", info.getOsName());
		o.addProperty("osVersion", info.getOsVersion());
		o.addProperty("osArch", info.getOsArch());
		o.addProperty("username", info.getUsername());
		o.add("variables", toJson(info.getVariables()));
		return o;
	}

	private JsonElement toJson(EclipseStatus status) {
		JsonObject o = new JsonObject();
		o.addProperty("message", status.getMessage());
		o.addProperty("code", status.getCode());
		o.addProperty("severity", status.getSeverity());
		// TODO o.addProperty("featureGuess", status.getFeatureGuess());
		o.addProperty("plugin", status.getPlugin());
		o.addProperty("thread", status.getThreadName());
		// TODO o.addProperty("children", null);
		JavaException e = status.getException();
		if (e != null)
			o.add("exception", toJson(e));
		return o;
	}

	private JsonElement toJson(JavaException t) {
		JsonObject o = new JsonObject();
		o.addProperty("message", t.getMessage());
		o.addProperty("class", t.getClassName());
		JavaException cause = t.getCause();
		if (cause != null)
			o.add("cause", toJson(cause));
		JsonArray stacktrace = new JsonArray();
		for (JavaStackTraceEntry e : t.getStacktrace()) {
			stacktrace.add(toJson(e));
		}
		o.add("stacktrace", stacktrace);
		return o;
	}

	private JsonElement toJson(JavaStackTraceEntry e) {
		JsonArray a = new JsonArray();
		a.add(new JsonPrimitive(e.getFileName()));
		a.add(new JsonPrimitive(e.getClassName()));
		a.add(new JsonPrimitive(e.getMethodName()));
		a.add(new JsonPrimitive(e.getLineNumber()));
		if (e.isNative())
			a.add(new JsonPrimitive(true));
		return a;
	}

	private JsonElement toJson(EList<String> list) {
		JsonArray o = new JsonArray();
		for (String e : list) {
			o.add(new JsonPrimitive(e));
		}
		return o;
	}

	private JsonElement toJson(EMap<String, String> map) {
		JsonObject o = new JsonObject();
		for (Entry<String, String> entry : map.entrySet()) {
			o.addProperty(entry.getKey(), entry.getValue());
		}
		return o;
	}
}
