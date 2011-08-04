package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.xored.sherlock.core.info.Info;
import com.xored.x5agent.core.X5SnapshotProvider;

public class SystemInfoSnapshotProvider implements X5SnapshotProvider {

	private EObject snapshot;

	@Override
	public String getSchema() {
		return "system_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public EObject getSnapshot() {
		if (snapshot == null) {
			snapshot = Info.getSystem();
			// snapshot = new JsonObject();
			// Gson gson = new Gson();
			// snapshot.addProperty("osName", SystemInfoProvider.getOSName());
			// snapshot.addProperty("osVersion",
			// SystemInfoProvider.getOSVersion());
			// snapshot.addProperty("osArch", SystemInfoProvider.getOSArch());
			// snapshot.addProperty("username",
			// SystemInfoProvider.getUsername());
			// snapshot.add("variables",
			// gson.toJsonTree(SystemInfoProvider.getVariables()));
		}
		return snapshot;
	}

	@Override
	public void dispose() {
	}
}
