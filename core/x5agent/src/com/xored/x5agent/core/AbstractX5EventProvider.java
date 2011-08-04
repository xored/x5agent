package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public abstract class AbstractX5EventProvider implements X5EventProvider {

	private final List<X5EventListener> listeners = new ArrayList<X5EventListener>();

	@Override
	public void addListener(X5EventListener listener) {
		synchronized (this) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeListener(X5EventListener listener) {
		synchronized (this) {
			listeners.remove(listener);
		}
	}

	protected void notifyListeners(EObject eObject) {
		synchronized (this) {
			for (X5EventListener l : listeners) {
				l.notify(eObject);
			}
		}
	}

}
