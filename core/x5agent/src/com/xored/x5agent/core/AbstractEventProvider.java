package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEventProvider implements IEventProvider {

	private final List<IEventListener> listeners = new ArrayList<IEventListener>();

	@Override
	public void addListener(IEventListener listener) {
		synchronized (this) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeListener(IEventListener listener) {
		synchronized (this) {
			listeners.remove(listener);
		}
	}

	protected void notifyListeners(Object o) {
		synchronized (this) {
			for (IEventListener l : listeners) {
				l.handle(o);
			}
		}
	}

}
