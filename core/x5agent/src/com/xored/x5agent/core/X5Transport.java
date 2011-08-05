package com.xored.x5agent.core;

import java.util.Map;

import com.xored.x5agent.model.X5Request;
import com.xored.x5agent.model.X5Response;

public interface X5Transport {
	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object.
	 */
	void initialize(X5Agent agent, Map<String, String> parameters);

	/**
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
