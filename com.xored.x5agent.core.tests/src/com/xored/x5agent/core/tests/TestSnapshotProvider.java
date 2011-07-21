package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.ISnapshot;
import com.xored.x5agent.core.ISnapshotProvider;

public class TestSnapshotProvider extends Provider implements ISnapshotProvider {

	@Override
	public ISnapshot getSnapshot() {
		return new ISnapshot() {
		};
	}

}
