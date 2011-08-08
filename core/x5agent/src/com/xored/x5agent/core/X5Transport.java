package com.xored.x5agent.core;

import java.io.IOException;
import java.util.Map;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;

public interface X5Transport {
	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object.
	 * 
	 * @throws IOException when parameters are valid, but transport could not start. In this case X5 agent will try to restart transport later
	 * IllegalArgumentException when parameters are invalid. X5 agent won't try to reinitialize this transport until parameters are changed
	 */
	void initialize(Map<String, String> parameters) throws IOException;

	/**
	 * Must be thread-safe.
	 * 
	 * @param request
	 *            Request to send to the destination.
	 */
	X5Response send(X5Request request) throws IOException;

	/**
	 * When X5 does not need transport object any longer, it calls dispose on
	 * it. Nothing will be called on this object after dispose() returns. This
	 * method should not throw any exceptions.
	 */
	void dispose();
}
