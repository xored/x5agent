package com.xored.x5server.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xored.emfjson.Emf2Json;
import com.xored.sherlock.core.model.sherlock.SherlockPackage;
import com.xored.x5agent.model.X5Package;

public abstract class AbstractX5Server implements X5Server {

	// TODO read from configuration
	private static final EPackage[] packages = new EPackage[] {
			X5Package.eINSTANCE, SherlockPackage.eINSTANCE };

	@Override
	public EObject eObjectFromJson(String json) {
		Emf2Json emf2Json = new Emf2Json(packages);
		JsonParser p = new JsonParser();
		JsonElement e = p.parse(json);
		if (e instanceof JsonObject) {
			EObject eObject = emf2Json.deserialize((JsonObject) e);
			return eObject;
		}
		throw new IllegalArgumentException("Unexpected format");
	}

	@Override
	public EObject eObjectFromBytes(byte[] bytes) {
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
}
