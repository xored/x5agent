package com.xored.x5server.core;

import org.eclipse.emf.ecore.EObject;

import com.xored.x5agent.model.X5Request;
import com.xored.x5agent.model.X5Response;

public interface X5Server {

	X5Response handle(X5Request request);

	EObject eObjectFromJson(String json);

	EObject eObjectFromBytes(byte[] bytes);

	void logInfo(String message);

	void logError(Throwable t);

}
