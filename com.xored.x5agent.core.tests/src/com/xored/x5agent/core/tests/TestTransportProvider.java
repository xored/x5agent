package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.ITransportProvider;

public class TestTransportProvider extends Provider implements
		ITransportProvider {

	@Override
	public void initialize(IConfiguration configuration) {
		for (String n : configuration.names()) {
			System.out.println(n + ":" + configuration.value(n));
		}
	}

	@Override
	public void send(String message) {
		System.out.println(message);
	}

	@Override
	public void dispose() {
	}

}
