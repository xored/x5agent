package com.xored.x5agent.core;

import java.util.Map;

public interface X5EventProvider {
	/**
	 * @return event provider schema name
	 */
	String getSchema();

	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object. Event provider should start generate events only after
	 * initialization. If any exception is thrown event provider is considered
	 * as broken and will not be used for event listening.
	 */
	void initialize(Map<String, String> parameters);

	/**
	 * Will be called after object’s initialization to add new listener. Event
	 * provider must notify all the added listener about new events.
	 * 
	 * @param listener
	 */
	void addListener(X5EventListener listener);

	/**
	 * Will be called after object’s initialization to remove a listener. Event
	 * provider must stop notify removed listener about new events.
	 * 
	 * @param listener
	 */
	void removeListener(X5EventListener listener);

	/**
	 * When X5 does not need event provider object any longer, it calls dispose
	 * on it. Nothing will be called on this object after dispose() returns.
	 * This method should not throw any exceptions.
	 */
	void dispose();

}
