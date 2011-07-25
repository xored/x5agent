package com.xored.x5agent.core;

public interface IStreamDescriber {

	IEventDescriber[] events();

	ITransportDescriber transport();
}
