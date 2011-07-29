package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.ILogger;

import com.google.gson.JsonObject;
import com.xored.emfjson.Emf2Json;
import com.xored.sherlock.core.SherlockCore;
import com.xored.sherlock.core.model.sherlock.EclipseStatus;
import com.xored.sherlock.core.model.sherlock.SherlockPackage;
import com.xored.sherlock.jface.SherlockJFacePolicy;

public class JFacePolicyEventProvider extends AbstractEventProvider implements
		ILogger {
	@Override
	public String getType() {
		return "EclipseStatus";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		SherlockJFacePolicy.Instance.addListener(this);
	}

	@Override
	public void dispose() {
		SherlockJFacePolicy.Instance.removeListener(this);
	}

	@Override
	public void log(IStatus status) {
		EclipseStatus data = SherlockCore.convert(status);
		Emf2Json converter = new Emf2Json(SherlockPackage.eINSTANCE);
		JsonObject jsonObject = converter.serialize(data);
		notifyListeners(jsonObject);
	}

}
