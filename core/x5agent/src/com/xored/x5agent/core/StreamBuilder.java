package com.xored.x5agent.core;


public interface StreamBuilder {

	EventBuilder addEvent();

	TransportBuilder addTransport();

	interface EventBuilder extends StreamBuilder {

		EventBuilder addEventParam(String key, String value);

		EventBuilder withEventClass(Class<?> clazz);

		SnapshotBuilder addSnapshot();

		interface SnapshotBuilder extends StreamBuilder {

			SnapshotBuilder addSnapshotParam(String key, String value);

			SnapshotBuilder withSnapshotClass(Class<?> clazz);

		}

	}

	interface TransportBuilder extends StreamBuilder {

		TransportBuilder addTransportParam(String key, String value);

		TransportBuilder withTransportClass(Class<?> clazz);
	}

}
