package com.xored.x5agent.core;

import java.util.Map;

public interface ISnapshotProvider {

	String type();

	void initialize(Map<String, String> parameters);

	Object getSnapshot();

	void dispose();
}
