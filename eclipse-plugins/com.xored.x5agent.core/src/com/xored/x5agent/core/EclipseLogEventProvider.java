package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

import com.google.gson.JsonObject;
import com.xored.emfjson.Emf2Json;
import com.xored.sherlock.core.SherlockCore;
import com.xored.sherlock.core.model.sherlock.EclipseStatus;
import com.xored.sherlock.core.model.sherlock.SherlockPackage;

public class EclipseLogEventProvider extends AbstractEventProvider implements
		ILogListener {

	@Override
	public String getType() {
		return "sherlock.eclipse_status";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		SherlockCore.addLogListener(this, null, null);
	}

	@Override
	public void dispose() {
		SherlockCore.removeLogListener(this);
	}

	@Override
	public void logging(IStatus status, String plugin) {
		EclipseStatus data = SherlockCore.convert(status);
		Emf2Json converter = new Emf2Json(SherlockPackage.eINSTANCE);
		JsonObject jsonObject = converter.serialize(data);
		notifyListeners(jsonObject);
	}

}
