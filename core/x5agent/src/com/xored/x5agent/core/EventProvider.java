package com.xored.x5agent.core;

import java.util.Map;

public interface EventProvider {

	String getType();

	void initialize(Map<String, String> parameters);

	void addListener(EventListener listener);

	void removeListener(EventListener listener);

	void dispose();

}
