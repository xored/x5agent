package com.xored.x5agent.core;

import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.google.gson.JsonObject;
import com.xored.emfjson.Emf2Json;

public enum X5Agent {
	Instance;

	private final Logger logger = Logger.getLogger("com.xored.x5agent.core");

	private X5Stream[] streams;
	private EPackage[] packages;
	private X5Preferences preferences;

	public void initialize(X5Preferences preferences, EPackage[] packages,
			X5Stream[] streams) {
		if (preferences == null)
			throw new NullPointerException("X5 preferences is null");
		this.preferences = preferences;
		this.packages = packages;
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

	public Logger getLog() {
		return logger;
	}

	public JsonObject eObjectToJson(EObject eObject) {
		Emf2Json serializer = new Emf2Json(packages);
		JsonObject json = serializer.serialize(eObject);
		return json;
	}

}
