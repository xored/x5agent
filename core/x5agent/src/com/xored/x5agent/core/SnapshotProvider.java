package com.xored.x5agent.core;

import java.util.Map;

public interface SnapshotProvider {

	String getType();

	void initialize(Map<String, String> parameters);

	Object getSnapshot();

	void dispose();
}
