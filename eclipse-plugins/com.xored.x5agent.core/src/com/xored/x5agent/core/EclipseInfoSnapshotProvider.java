package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.xored.sherlock.core.info.Info;

public class EclipseInfoSnapshotProvider implements X5SnapshotProvider {

	private EObject snapshot;

	@Override
	public String getSchema() {
		return "sherlock.eclipse_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public EObject getSnapshot() {
		if (snapshot == null) {
			snapshot = Info.getEclipse();
		}
		return snapshot;
	}

	@Override
	public void dispose() {
	}
}
