package com.xored.x5agent.core;

import java.util.Map;

public interface IEventProvider {

	String type();

	void initialize(Map<String, String> parameters);

	void addListener(IEventListener listener);

	void removeListener(IEventListener listener);

	void dispose();

}
