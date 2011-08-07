package com.xored.x5;

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

public class EmfSerializer {

	public String eObjectToJson(EObject eObject) {
		Emf2Json emf2Json = new Emf2Json();
		JsonObject json = emf2Json.serialize(eObject);
		return json.toString();
	}

	public EObject jsonToEObject(String json) {
		Emf2Json emf2Json = new Emf2Json();
		JsonParser p = new JsonParser();
		JsonElement e = p.parse(json);
		if (e instanceof JsonObject) {
			EObject eObject = emf2Json.deserialize((JsonObject) e);
			return eObject;
		}
		throw new IllegalArgumentException("Unexpected format");
	}

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
}
