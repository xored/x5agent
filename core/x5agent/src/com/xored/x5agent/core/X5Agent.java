package com.xored.x5agent.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gson.JsonObject;
import com.xored.emfjson.Emf2Json;

public enum X5Agent {
	Instance;

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

	public void logInfo(String message) {
		System.out.println(message);
	}

	public void logError(Throwable t) {
		t.printStackTrace();
	}

	public String eObjectToJson(EObject eObject) {
		Emf2Json emf2Json = new Emf2Json(packages);
		JsonObject json = emf2Json.serialize(eObject);
		return json.toString();
	}

	public byte[] eObjectToBytes(EObject eObject) {
		Resource r = new BinaryResourceImpl();
		r.getContents().add(eObject);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			r.save(out, null);
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("Failed to serialize", e);
		}
	}
}
