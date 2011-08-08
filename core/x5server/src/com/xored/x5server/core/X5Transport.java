package com.xored.x5server.core;

import java.util.Map;

public interface X5Transport {

	void initialize(Map<String, String> parameters, X5RequestHandler handler);

	void dispose();

}
