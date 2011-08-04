package com.xored.x5agent.core;

public interface X5StreamBuilder {

	X5EventBuilder addEvent();

	X5TransportBuilder addTransport();

	interface X5EventBuilder extends X5StreamBuilder {

		X5EventBuilder addEventParam(String key, String value);

		X5EventBuilder withEventClass(Class<?> clazz);

		X5SnapshotBuilder addSnapshot();

		interface X5SnapshotBuilder extends X5StreamBuilder {

			X5SnapshotBuilder addSnapshotParam(String key, String value);

			X5SnapshotBuilder withSnapshotClass(Class<?> clazz);

		}

	}

	interface X5TransportBuilder extends X5StreamBuilder {

		X5TransportBuilder addTransportParam(String key, String value);

		X5TransportBuilder withTransportClass(Class<?> clazz);
	}

}
