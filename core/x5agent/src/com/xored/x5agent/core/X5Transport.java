package com.xored.x5agent.core;

import java.util.Map;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;

public interface X5Transport {
	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object.
	 */
	void initialize(Map<String, String> parameters);

	/**
	 * Must be thread-safe.
	 * 
	 * @param request
	 *            Request to send to the destination.
	 */
	X5Response send(X5Request request);

	/**
	 * When X5 does not need transport object any longer, it calls dispose on
	 * it. Nothing will be called on this object after dispose() returns. This
	 * method should not throw any exceptions.
	 */
	void dispose();
}
