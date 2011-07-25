package com.xored.x5agent.core.tests;

import java.util.Map;

import com.xored.x5agent.core.IDeliveryCallback;
import com.xored.x5agent.core.ITransport;

public class TestTransportProvider extends TestProvider implements ITransport {

	@Override
	public void initialize(Map<String, String> parameters,
			IDeliveryCallback callback) {
		super.initialize(parameters);
	}

	@Override
	public void send(String message, String id) {
		System.out.println(message);
	}

}
