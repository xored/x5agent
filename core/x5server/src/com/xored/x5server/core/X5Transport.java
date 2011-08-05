package com.xored.x5server.core;

import java.util.Map;

public interface X5Transport {

	void initialize(X5Server server, Map<String, String> parameters);

	void dispose();

}
