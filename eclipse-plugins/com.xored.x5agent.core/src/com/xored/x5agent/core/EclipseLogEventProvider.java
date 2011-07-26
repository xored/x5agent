package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

import com.xored.sherlock.core.SherlockCore;

public class EclipseLogEventProvider extends AbstractEventProvider implements
		ILogListener {

	@Override
	public String type() {
		return "EclipseStatus";
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
		notifyListeners(status);
	}

}
