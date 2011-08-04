package com.xored.x5agent.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

import com.xored.x5agent.core.X5StreamDescriptor.X5EventDescriptor;
import com.xored.x5agent.core.X5StreamDescriptor.X5SnapshotDescriptor;
import com.xored.x5agent.core.X5StreamDescriptor.X5TransportDescriptor;

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

	public X5Stream[] getStreams() {
		List<X5Stream> streamDescribers = new ArrayList<X5Stream>();
		for (IConfigurationElement e : Platform.getExtensionRegistry()
				.getConfigurationElementsFor(STREAMS)) {
			XPStreamDescriptor descriptor = new XPStreamDescriptor(e);
			streamDescribers.add(X5Stream.create(descriptor));
		}
		return streamDescribers.toArray(new X5Stream[streamDescribers.size()]);
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

	private final class XPStreamDescriptor implements X5StreamDescriptor {

		private final IConfigurationElement element;
		private X5EventDescriptor[] events;
		private XPTransportDescriptor transport;

		private XPStreamDescriptor(IConfigurationElement element) {
			Assert.isNotNull(element);
			this.element = element;
		}

		@Override
		public X5EventDescriptor[] events() {
			if (events == null) {
				IConfigurationElement[] elements = element
						.getChildren(EVENT_ELEMENT);
				List<X5EventDescriptor> describers = new ArrayList<X5EventDescriptor>(
						elements.length);
				for (IConfigurationElement e : elements) {
					describers.add(new XPEventDescriptor(e));
				}
				events = describers.toArray(new X5EventDescriptor[describers
						.size()]);
			}
			return events;
		}

		@Override
		public X5TransportDescriptor transport() {
			if (transport == null) {
				IConfigurationElement[] elements = element
						.getChildren(TRANSPORT_ELEMENT);
				transport = new XPTransportDescriptor(elements[0]);
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

	private final class XPEventDescriptor extends Configurable implements
			X5EventDescriptor {

		private XPSnapshotDescriptor[] snapshots;

		private XPEventDescriptor(IConfigurationElement element) {
			super(element);
		}

		@Override
		public X5EventProvider create() {
			return (X5EventProvider) doCreate();
		}

		public XPSnapshotDescriptor[] snapshots() {
			if (snapshots == null) {
				IConfigurationElement[] elements = element
						.getChildren(SNAPSHOT_ELEMENT);
				List<XPSnapshotDescriptor> describers = new ArrayList<XPSnapshotDescriptor>(
						elements.length);
				for (IConfigurationElement e : elements) {
					describers.add(new XPSnapshotDescriptor(e));
				}
				snapshots = describers
						.toArray(new XPSnapshotDescriptor[describers.size()]);
			}
			return snapshots;
		}
	}

	private final class XPSnapshotDescriptor extends Configurable implements
			X5SnapshotDescriptor {

		private XPSnapshotDescriptor(IConfigurationElement element) {
			super(element);
		}

		@Override
		public X5SnapshotProvider create() {
			return (X5SnapshotProvider) doCreate();
		}
	}

	private final class XPTransportDescriptor extends Configurable implements
			X5TransportDescriptor {

		protected XPTransportDescriptor(IConfigurationElement element) {
			super(element);
		}

		@Override
		public X5Transport create() {
			return (X5Transport) doCreate();
		}
	}

}
