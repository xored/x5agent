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

	public void initialize(EPackage[] packages, X5Stream[] streams) {
		this.packages = packages;
		this.streams = streams;
		for (X5Stream stream : streams) {
			stream.initialize();
		}
	}

	public void dispose() {
		for (X5Stream stream : streams) {
			stream.dispose();
		}
	}

	public String getClientApp() {
		// TODO store in preferences
		return "2c01adaf-3a40-430b-9aa9-551daf81ad90";
	}

	public String getClient() {
		// TODO store in preferences
		return "xored-test";
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
