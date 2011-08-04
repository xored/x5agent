package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public interface X5SnapshotProvider {
	/**
	 * @return snapshot provider schema name
	 */
	String getSchema();

	/**
	 * Will be called after object’s construction, but before any actual usage
	 * of the object. If any exception is thrown snapshot provider is considered
	 * as broken.
	 */
	void initialize(Map<String, String> parameters);

	/**
	 * @return snapshot or null if not available at this moment
	 */
	EObject getSnapshot();

	/**
	 * When X5 does not need snapshot provider object any longer, it calls
	 * dispose on it. Nothing will be called on this object after dispose()
	 * returns. This method should not throw any exceptions.
	 */
	void dispose();
}
