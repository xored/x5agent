package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.xored.sherlock.core.info.Info;
import com.xored.x5agent.core.X5SnapshotProvider;

public class JavaInfoSnapshotProvider implements X5SnapshotProvider {

	private EObject snapshot;

	@Override
	public String getSchema() {
		return "java_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public EObject getSnapshot() {
		if (snapshot == null) {
			snapshot = Info.getJava();
			// snapshot = new JsonObject();
			// Gson gson = new Gson();
			// snapshot.addProperty("runtimeVersion",
			// JavaInfoProvider.getRuntimeVersion());
			// snapshot.addProperty("runtimeName",
			// JavaInfoProvider.getRuntimeName());
			// snapshot.addProperty("totalMemory",
			// JavaInfoProvider.getTotalMemory());
			// snapshot.addProperty("maxMemory",
			// JavaInfoProvider.getMaxMemory());
			// snapshot.addProperty("freeMemory",
			// JavaInfoProvider.getFreeMemory());
			// snapshot.add("properties",
			// gson.toJsonTree(JavaInfoProvider.getProperties()));
			// snapshot.add("args",
			// gson.toJsonTree(JavaInfoProvider.getCommandLineArgs()));
		}
		return snapshot;
	}

	@Override
	public void dispose() {
	}

}
