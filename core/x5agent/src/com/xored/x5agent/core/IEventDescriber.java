package com.xored.x5agent.core;

import java.util.Map;

public interface IEventDescriber {

	IEventProvider create();

	Map<String, String> parameters();

	ISnapshotDescriber[] snapshots();
}
