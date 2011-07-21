package com.xored.x5agent.internal.core;

import com.xored.x5agent.core.IMessage;

public interface IMessageListener {
	void handle(IMessage message);
}
