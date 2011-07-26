package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.ILogger;

import com.xored.sherlock.jface.SherlockJFacePolicy;

public class JFacePolicyEventProvider extends AbstractEventProvider implements
		ILogger {
	@Override
	public String type() {
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
		notifyListeners(status);
	}

}
