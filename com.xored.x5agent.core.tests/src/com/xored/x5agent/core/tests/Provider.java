package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.IProvider;

public class Provider implements IProvider {

	@Override
	public void initialize(IConfiguration configuration) {
		for (String n : configuration.names()) {
			System.out.println(n + ":" + configuration.value(n));
		}
	}

	@Override
	public void dispose() {
	}

}
