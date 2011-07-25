package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

public enum X5AgentRegistry {
	Instance;

	private static final String STREAMS = Activator.PLUGIN_ID + ".streams"; //$NON-NLS-1$

	private static final String EVENT_ELEMENT = "event"; //$NON-NLS-1$
	private static final String SNAPSHOT_ELEMENT = "snapshot"; //$NON-NLS-1$
	private static final String TRANSPORT_ELEMENT = "transport"; //$NON-NLS-1$

	private static final String PARAMETER_ELEMENT = "parameter"; //$NON-NLS-1$
	private static final String NAME_ATTR = "name"; //$NON-NLS-1$
	private static final String VALUE_ATTR = "value"; //$NON-NLS-1$

	private static final String CLASS_ATTR = "class"; //$NON-NLS-1$

	public Collection<IStreamDescriber> getStreams() {
		List<IStreamDescriber> streamDescribers = new ArrayList<IStreamDescriber>();
		for (IConfigurationElement e : Platform.getExtensionRegistry()
				.getConfigurationElementsFor(STREAMS)) {
			streamDescribers.add(new StreamDescriber(e));
		}
		return streamDescribers;
	}

	private Map<String, String> getProperties(IConfigurationElement element) {
		Map<String, String> properties = new HashMap<String, String>();
		for (IConfigurationElement p : element.getChildren(PARAMETER_ELEMENT)) {
			String name = p.getAttribute(NAME_ATTR);
			String value = p.getAttribute(VALUE_ATTR);
			if (properties.containsKey(name)) {
				Activator.logError(NLS.bind("Duplicate property name: {0}. "
						+ "Property is ignored", name));
			} else {
				properties.put(name, value);
			}
		}
		return properties;
	}

	private final class StreamDescriber implements IStreamDescriber {

		private final IConfigurationElement element;
		private IEventDescriber[] events;
		private ITransportDescriber transport;

		private StreamDescriber(IConfigurationElement element) {
			Assert.isNotNull(element);
			this.element = element;
		}

		@Override
		public IEventDescriber[] events() {
			if (events == null) {
				IConfigurationElement[] elements = element
						.getChildren(EVENT_ELEMENT);
				List<IEventDescriber> describers = new ArrayList<IEventDescriber>(
						elements.length);
				for (IConfigurationElement e : elements) {
					describers.add(new EventDescriber(e));
				}
				events = describers.toArray(new IEventDescriber[describers
						.size()]);
			}
			return events;
		}

		@Override
		public ITransportDescriber transport() {
			if (transport == null) {
				IConfigurationElement[] elements = element
						.getChildren(TRANSPORT_ELEMENT);
				transport = new TransportDescriber(elements[0]);
			}
			return transport;
		}
	}

	private class Configurable {
		protected final IConfigurationElement element;
		private Map<String, String> parameters;

		protected Configurable(IConfigurationElement element) {
			Assert.isNotNull(element);
			this.element = element;
		}

		public Object doCreate() {
			try {
				return element.createExecutableExtension(CLASS_ATTR);
			} catch (CoreException e) {
				Activator.log(e);
			}
			return null;
		}

		public Map<String, String> parameters() {
			if (parameters == null) {
				parameters = getProperties(element);
			}
			return parameters;
		}
	}

	private final class EventDescriber extends Configurable implements
			IEventDescriber {

		private ISnapshotDescriber[] snapshots;

		private EventDescriber(IConfigurationElement element) {
			super(element);
		}

		@Override
		public IEventProvider create() {
			return (IEventProvider) doCreate();
		}

		public ISnapshotDescriber[] snapshots() {
			if (snapshots == null) {
				IConfigurationElement[] elements = element
						.getChildren(SNAPSHOT_ELEMENT);
				List<ISnapshotDescriber> describers = new ArrayList<ISnapshotDescriber>(
						elements.length);
				for (IConfigurationElement e : elements) {
					describers.add(new SnapshotDescriber(e));
				}
				snapshots = describers
						.toArray(new ISnapshotDescriber[describers.size()]);
			}
			return snapshots;
		}
	}

	private final class SnapshotDescriber extends Configurable implements
			ISnapshotDescriber {

		private SnapshotDescriber(IConfigurationElement element) {
			super(element);
		}

		@Override
		public ISnapshotProvider create() {
			return (ISnapshotProvider) doCreate();
		}
	}

	private final class TransportDescriber extends Configurable implements
			ITransportDescriber {

		protected TransportDescriber(IConfigurationElement element) {
			super(element);
		}

		@Override
		public ITransport create() {
			return (ITransport) doCreate();
		}
	}

}
