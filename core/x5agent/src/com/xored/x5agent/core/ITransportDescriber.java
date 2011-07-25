package com.xored.x5agent.core;

import java.util.Map;

public interface ITransportDescriber {

	ITransport create();

	Map<String, String> parameters();
}
