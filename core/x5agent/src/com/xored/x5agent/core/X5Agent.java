package com.xored.x5agent.core;

public enum X5Agent {
	Instance;

	private X5Stream[] streams;
	private X5Preferences preferences;

	public void initialize(X5Preferences preferences, X5Stream[] streams) {
		if (preferences == null)
			throw new NullPointerException("X5 preferences is null");
		this.preferences = preferences;
		this.streams = streams;
		if (this.streams != null) {
			for (X5Stream stream : this.streams) {
				stream.initialize();
			}
		}
	}

	public void dispose() {
		if (streams != null) {
			for (X5Stream stream : streams) {
				stream.dispose();
			}
		}
	}

	public String getClientApp() {
		String clientApp = preferences.getClientApp();
		if (preferences == null)
			throw new NullPointerException("Client Application Id is null");
		return clientApp;
	}

	public String getClient() {
		String client = preferences.getClient();
		if (preferences == null)
			throw new NullPointerException("Client is null");
		return client;
	}

	public void logInfo(String message) {
		// TODO use logger
		System.out.println(message);
	}

	public void logError(Throwable t) {
		// TODO use logger
		t.printStackTrace();
	}
}
