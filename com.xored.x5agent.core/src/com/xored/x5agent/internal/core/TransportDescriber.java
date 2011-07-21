package com.xored.x5agent.internal.core;

import org.eclipse.core.runtime.Assert;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.IMessage;
import com.xored.x5agent.core.ITransportProvider;

public class TransportDescriber {

	private final String providerId;
	private ITransportProvider provider;
	private final IConfiguration configuration;

	public TransportDescriber(String providerId, IConfiguration configuration) {
		Assert.isNotNull(providerId);
		Assert.isNotNull(configuration);
		this.providerId = providerId;
		this.configuration = configuration;
	}

	public void initialize() {
		provider = X5AgentRegistry.Instance.createTransportProvider(providerId);
		provider.initialize(configuration);
	}

	public void dispose() {
		provider.dispose();
	}

	public void send(IMessage m) {
		provider.send(JsonConverter.Instance.convert(m));
	}
}
