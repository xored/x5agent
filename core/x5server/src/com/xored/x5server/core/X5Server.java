package com.xored.x5server.core;

import org.eclipse.emf.ecore.EObject;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;

public interface X5Server {

	X5Response handle(X5Request request);

	void logInfo(String message);

	void logError(Throwable t);

	String eObjectToJson(EObject eObject);

	byte[] eObjectToByteArray(EObject eObject);

	EObject jsonToEObject(String json);

	EObject byteArrayToEObject(byte[] bytes);

}
