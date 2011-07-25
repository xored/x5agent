package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.ISnapshotProvider;

public class TestSnapshotProvider extends TestProvider implements
		ISnapshotProvider {

	private final Snapshot snapshot = new Snapshot();

	@Override
	public String type() {
		return getClass().getName();
	}

	@Override
	public Object getSnapshot() {
		return snapshot;
	}

	private static final class Snapshot {
		@SuppressWarnings("unused")
		private final String name = "test snapshot";
	}

}
