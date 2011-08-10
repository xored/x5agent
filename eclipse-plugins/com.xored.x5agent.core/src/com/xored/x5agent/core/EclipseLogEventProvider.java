package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

import com.xored.sherlock.core.SherlockCore;
import com.xored.sherlock.core.model.sherlock.EclipseStatus;

public class EclipseLogEventProvider extends StatusEventProvider implements
		ILogListener {

	@Override
	public void initialize(Map<String, String> parameters) {
		SherlockCore.addLogListener(this, null, null);
	}

	@Override
	public void logging(IStatus status, String plugin) {
		EclipseStatus eStatus = SherlockCore.convert(status);
		eStatus.setThreadName(Thread.currentThread().getName());
		notifyListeners(eStatus);
	}

	@Override
	public void dispose() {
		SherlockCore.removeLogListener(this);
	}

}
