package com.xored.x5agent.core;

public interface IEventProvider extends IProvider {

	void addListener(IEventListener listener);

	void removeListener(IEventListener listener);

}
