package com.xored.x5agent.internal.core;

import org.eclipse.core.runtime.Assert;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.ISnapshot;
import com.xored.x5agent.core.ISnapshotProvider;

public class SnapshotDescriber {

	private final String providerId;
	private ISnapshotProvider provider;
	private final IConfiguration configuration;

	public SnapshotDescriber(String providerId, IConfiguration configuration) {
		Assert.isNotNull(providerId);
		Assert.isNotNull(configuration);
		this.providerId = providerId;
		this.configuration = configuration;
	}

	public String id() {
		return providerId;
	}

	public void initialize() {
		provider = X5AgentRegistry.Instance.createSnapshotProvider(providerId);
		provider.initialize(configuration);
	}

	public void dispose() {
		provider.dispose();
	}

	public ISnapshot getSnapshot() {
		return provider.getSnapshot();
	}

}
