package com.xored.x5agent.core;

import java.util.UUID;

public interface IMessage {

	/**
	 * @return uuid or null
	 */
	UUID id();

	long getTimestamp();

	String getType();

	UUID[] getReferences();

}
