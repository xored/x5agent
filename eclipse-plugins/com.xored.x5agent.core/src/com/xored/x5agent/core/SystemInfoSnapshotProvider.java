package com.xored.x5agent.core;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xored.sherlock.core.info.SystemInfoProvider;

public class SystemInfoSnapshotProvider implements SnapshotProvider {

	private JsonObject snapshot;

	@Override
	public String getType() {
		return "system_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public Object getSnapshot() {
		if (snapshot == null) {
			snapshot = new JsonObject();
			Gson gson = new Gson();
			snapshot.addProperty("osName", SystemInfoProvider.getOSName());
			snapshot.addProperty("osVersion", SystemInfoProvider.getOSVersion());
			snapshot.addProperty("osArch", SystemInfoProvider.getOSArch());
			snapshot.addProperty("username", SystemInfoProvider.getUsername());
			snapshot.add("variables",
					gson.toJsonTree(SystemInfoProvider.getVariables()));
		}
		return snapshot;
	}

	@Override
	public void dispose() {
	}
}
