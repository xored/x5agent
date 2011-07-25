package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public enum X5Agent {
	Instance;

	private final List<Stream> streams = new ArrayList<Stream>();

	public void initialize() {
	}

	public void run(IStreamDescriber describer) {
		Stream s = new Stream(describer);
		streams.add(s);
		s.initialize();
	}

	public void dispose() {
		for (Stream s : streams) {
			s.dispose();
		}
	}

	public UUID getClientUUID() {
		// TODO
		return UUID.randomUUID();
	}

	public String getClientApp() {
		// TODO
		return "xored";
	}

}
