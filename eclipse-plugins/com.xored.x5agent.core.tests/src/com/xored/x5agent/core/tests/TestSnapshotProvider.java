package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.ISnapshotProvider;

public class TestSnapshotProvider extends TestProvider implements
		ISnapshotProvider {

	@Override
	public String type() {
		return getClass().getName();
	}

	@Override
	public Object getSnapshot() {
		return new Object();
	}

}
