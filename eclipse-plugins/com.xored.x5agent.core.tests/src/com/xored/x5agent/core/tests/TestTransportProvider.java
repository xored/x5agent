package com.xored.x5agent.core.tests;

import java.util.Map;

import com.xored.x5agent.core.DeliveryCallback;
import com.xored.x5agent.core.Message;
import com.xored.x5agent.core.Transport;

public class TestTransportProvider extends TestProvider implements Transport {

	@Override
	public void initialize(Map<String, String> parameters,
			DeliveryCallback callback) {
		super.initialize(parameters);
	}

	@Override
	public void send(Message[] messages) {
		for (Message m : messages) {
			System.out.println(m);
		}
	}

}
