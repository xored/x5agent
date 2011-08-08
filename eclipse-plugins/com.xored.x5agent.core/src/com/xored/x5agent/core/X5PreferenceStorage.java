package com.xored.x5agent.core;

import java.util.UUID;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

public enum X5PreferenceStorage implements X5Preferences {

	Instance;

	private static final String DEFAULT_CLIENT = "default";
	public static final String CLIENT_APP_KEY = "clientApp";
	public static final String CLIENT_KEY = "client";

	@Override
	public String getClientApp() {
		IEclipsePreferences node = new InstanceScope()
				.getNode(Activator.PLUGIN_ID);
		String app = node.get(CLIENT_APP_KEY, null);
		if (app == null) {
			app = UUID.randomUUID().toString();
			node.put(CLIENT_APP_KEY, app);
			try {
				node.flush();
			} catch (Exception e) {
				Activator.logError(e);
			}
		}
		return app;
	}

	@Override
	public String getClient() {
		IEclipsePreferences node = new InstanceScope()
				.getNode(Activator.PLUGIN_ID);
		String client = node.get(CLIENT_KEY, null);
		if (client == null) {
			client = DEFAULT_CLIENT;
			node.put(CLIENT_KEY, client);
			try {
				node.flush();
			} catch (Exception e) {
				Activator.logError(e);
			}
		}
		return client;
	}

	public void setClient(String client) {
		IEclipsePreferences node = new InstanceScope()
				.getNode(Activator.PLUGIN_ID);
		node.put(CLIENT_KEY, client);
		try {
			node.flush();
		} catch (Exception e) {
			Activator.logError(e);
		}
	}

	public void setDefaultClient() {
		IEclipsePreferences node = new InstanceScope()
				.getNode(Activator.PLUGIN_ID);
		node.put(CLIENT_KEY, DEFAULT_CLIENT);
		try {
			node.flush();
		} catch (Exception e) {
			Activator.logError(e);
		}
	}

}
