package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public enum X5Agent {
	Instance;

	private final Logger logger = Logger.getLogger("com.xored.x5agent.core");
	private final List<Stream> streams = new ArrayList<Stream>();

	public void initialize() {
	}

	public void run(Stream stream) {
		streams.add(stream);
		stream.initialize();
	}

	public void dispose() {
		for (Stream stream : streams) {
			stream.dispose();
		}
	}

	public UUID getClientUUID() {
		// TODO
		return UUID.fromString("2c01adaf-3a40-430b-9aa9-551daf81ad90");
	}

	public String getClientApp() {
		// TODO
		return "xored-test";
	}

	public Logger getLog() {
		return logger;
	}

}
