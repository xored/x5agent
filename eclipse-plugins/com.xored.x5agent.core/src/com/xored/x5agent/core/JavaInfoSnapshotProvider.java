package com.xored.x5agent.core;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xored.sherlock.core.info.JavaInfoProvider;

public class JavaInfoSnapshotProvider implements SnapshotProvider {

	private JsonObject snapshot;

	@Override
	public String getType() {
		return "java_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public Object getSnapshot() {
		if (snapshot == null) {
			snapshot = new JsonObject();
			Gson gson = new Gson();
			snapshot.addProperty("runtimeVersion",
					JavaInfoProvider.getRuntimeVersion());
			snapshot.addProperty("runtimeName",
					JavaInfoProvider.getRuntimeName());
			snapshot.addProperty("totalMemory",
					JavaInfoProvider.getTotalMemory());
			snapshot.addProperty("maxMemory", JavaInfoProvider.getMaxMemory());
			snapshot.addProperty("freeMemory", JavaInfoProvider.getFreeMemory());
			snapshot.add("properties",
					gson.toJsonTree(JavaInfoProvider.getProperties()));
			snapshot.add("args",
					gson.toJsonTree(JavaInfoProvider.getCommandLineArgs()));
		}
		return snapshot;
	}

	@Override
	public void dispose() {
	}

}
