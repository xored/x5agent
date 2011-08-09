package com.xored.x5server.core;

import java.io.IOException;
import java.util.Map;

public interface X5Transport {

	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object.
	 * 
	 * @throws IOException
	 *             when parameters are valid, but transport could not start. In
	 *             this case X5 will try to restart transport later.
	 * @throws IllegalArgumentException
	 *             when parameters are invalid. X5 won't try to reinitialize
	 *             this transport until parameters are changed.
	 * 
	 *             If transport throws any other kinds of exceptions the
	 *             transport is considered as invalid and will not be used any
	 *             longer.
	 */
	void initialize(Map<String, String> parameters, X5RequestHandler handler);

	/**
	 * When X5 does not need transport object any longer, it calls dispose on
	 * it. Nothing will be called on this object after dispose() returns.
	 */
	void dispose();

}
