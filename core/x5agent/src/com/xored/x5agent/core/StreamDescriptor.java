package com.xored.x5agent.core;

import java.util.Map;

public interface StreamDescriptor {
	EventDescriptor[] events();

	TransportDescriptor transport();

	interface EventDescriptor {
		EventProvider create();

		Map<String, String> parameters();

		SnapshotDescriptor[] snapshots();
	}

	interface SnapshotDescriptor {
		SnapshotProvider create();

		Map<String, String> parameters();
	}

	interface TransportDescriptor {
		Transport create();

		Map<String, String> parameters();
	}
}