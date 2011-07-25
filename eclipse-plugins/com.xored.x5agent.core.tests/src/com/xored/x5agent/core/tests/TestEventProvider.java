package com.xored.x5agent.core.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.xored.x5agent.core.IEventListener;
import com.xored.x5agent.core.IEventProvider;

public class TestEventProvider extends TestProvider implements IEventProvider,
		Runnable {

	private final List<IEventListener> listeners = new ArrayList<IEventListener>();
	private final AtomicBoolean disposed = new AtomicBoolean();

	@Override
	public String type() {
		return getClass().getName();
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		super.initialize(parameters);
		new Thread(this).start();
	}

	public void run() {
		for (int i = 0; !disposed.get(); i++) {
			synchronized (this) {
				for (IEventListener l : listeners) {
					l.handle(new Event(i));
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		disposed.set(true);
	}

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

	private static final class Event {
		@SuppressWarnings("unused")
		private final String name = "test event";
		@SuppressWarnings("unused")
		private final int number;

		public Event(int number) {
			this.number = number;
		}
	}

}
