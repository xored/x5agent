package com.xored.x5agent.core;

import java.util.Map;

public interface Transport {
	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object.Implementations should store the callback and use it to
	 * report delivery status back to X5 agent.
	 */
	void initialize(Map<String, String> parameters, DeliveryCallback callback);

	/**
	 * @param message
	 *            Message to send to the destination.
	 * @param id
	 *            Id may be used to report back message delivery status.
	 */
	void send(Message[] messages);

	/**
	 * When X5 does not need transport object any longer, it calls dispose on
	 * it. Nothing will be called on this object after dispose() returns. This
	 * method should not throw any exceptions.
	 */
	void dispose();
}
