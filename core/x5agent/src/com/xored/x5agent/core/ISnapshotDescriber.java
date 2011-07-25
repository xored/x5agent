package com.xored.x5agent.core;

import java.util.Map;

public interface ISnapshotDescriber {

	ISnapshotProvider create();

	Map<String, String> parameters();
}
