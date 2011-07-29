package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEventProvider implements EventProvider {

	private final List<EventListener> listeners = new ArrayList<EventListener>();

	@Override
	public void addListener(EventListener listener) {
		synchronized (this) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeListener(EventListener listener) {
		synchronized (this) {
			listeners.remove(listener);
		}
	}

	protected void notifyListeners(Object o) {
		synchronized (this) {
			for (EventListener l : listeners) {
				l.handle(o);
			}
		}
	}

}
