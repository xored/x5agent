package com.xored.x5agent.core;

import java.util.Map;

public interface X5StreamDescriptor {
	X5EventDescriptor[] events();

	X5TransportDescriptor transport();

	interface X5EventDescriptor {
		X5EventProvider create();

		Map<String, String> parameters();

		X5SnapshotDescriptor[] snapshots();
	}

	interface X5SnapshotDescriptor {
		X5SnapshotProvider create();

		Map<String, String> parameters();
	}

	interface X5TransportDescriptor {
		X5Transport create();

		Map<String, String> parameters();
	}
}