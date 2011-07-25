package com.xored.x5agent.core;

import java.util.Map;

public interface ITransport {

	void initialize(Map<String, String> parameters);

	void send(String message);

	void dispose();
}
