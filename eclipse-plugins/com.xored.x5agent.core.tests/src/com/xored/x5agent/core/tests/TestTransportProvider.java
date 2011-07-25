package com.xored.x5agent.core.tests;

import com.xored.x5agent.core.ITransport;

public class TestTransportProvider extends TestProvider implements
		ITransport {

	@Override
	public void send(String message) {
		System.out.println(message);
	}

}
