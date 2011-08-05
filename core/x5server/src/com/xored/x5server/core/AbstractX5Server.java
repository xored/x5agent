package com.xored.x5server.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xored.emfjson.Emf2Json;
import com.xored.sherlock.core.model.sherlock.SherlockPackage;
import com.xored.x5.X5Package;

public abstract class AbstractX5Server implements X5Server {

	@Override
	public String eObjectToJson(EObject eObject) {
		Emf2Json emf2Json = new Emf2Json(X5Package.eINSTANCE,
				SherlockPackage.eINSTANCE);
		JsonObject json = emf2Json.serialize(eObject);
		return json.toString();
	}

	@Override
	public byte[] eObjectToByteArray(EObject eObject) {
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

	@Override
	public EObject jsonToEObject(String json) {
		Emf2Json emf2Json = new Emf2Json(X5Package.eINSTANCE,
				SherlockPackage.eINSTANCE);
		JsonParser p = new JsonParser();
		JsonElement e = p.parse(json);
		if (e instanceof JsonObject) {
			EObject eObject = emf2Json.deserialize((JsonObject) e);
			return eObject;
		}
		throw new IllegalArgumentException("Unexpected format");
	}

	@Override
	public EObject byteArrayToEObject(byte[] bytes) {
		Resource r = new BinaryResourceImpl();
		ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
		try {
			r.load(bin, null);
			EObject eObject = r.getContents().get(0);
			return eObject;
		} catch (IOException e) {
			throw new RuntimeException("Failed to deserialize", e);
		}
	}

	@Override
	public void logInfo(String message) {
		// TODO use logger
		System.out.println(message);
	}

	@Override
	public void logError(Throwable t) {
		// TODO use logger
		t.printStackTrace();
	}
}
