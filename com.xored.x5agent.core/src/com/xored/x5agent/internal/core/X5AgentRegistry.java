package com.xored.x5agent.internal.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

import com.xored.x5agent.core.IConfiguration;
import com.xored.x5agent.core.IEventProvider;
import com.xored.x5agent.core.IProvider;
import com.xored.x5agent.core.ISnapshotProvider;
import com.xored.x5agent.core.ITransportProvider;

public enum X5AgentRegistry {
	Instance;

	private static final String STREAMS = Activator.PLUGIN_ID + ".streams"; //$NON-NLS-1$
	private static final String STREAM_EVENT_ELEMENT = "event"; //$NON-NLS-1$
	private static final String STREAM_TRANSPORT_ELEMENT = "transport"; //$NON-NLS-1$

	private static final String EVENTS = Activator.PLUGIN_ID + ".events"; //$NON-NLS-1$
	private static final String SNAPSHOTS = Activator.PLUGIN_ID + ".snapshots"; //$NON-NLS-1$
	private static final String TRANSPORTS = Activator.PLUGIN_ID
			+ ".transports"; //$NON-NLS-1$

	private static final String PROVIDER_ELEMENT = "provider"; //$NON-NLS-1$
	private static final String PROVIDER_ID_ATTR = "id"; //$NON-NLS-1$
	private static final String PROVIDER_CLASS_ATTR = "class"; //$NON-NLS-1$

	private static final String CONFIGURATION_ELEMENT = "configuration"; //$NON-NLS-1$
	private static final String CONFIGURATION_ID_ATTR = "id"; //$NON-NLS-1$
	private static final String CONFIGURATION_PROVIDER_ID_ATTR = "provider"; //$NON-NLS-1$
	private static final String CONFIGURATION_BASE_ID_ATTR = "base"; //$NON-NLS-1$
	private static final String CONFIGURATION_SNAPSHOT_ELEMENT = "snapshot"; //$NON-NLS-1$
	private static final String CONFIGURATION_PROPERTY_ELEMENT = "property"; //$NON-NLS-1$
	private static final String PROPERTY_NAME_ATTR = "name"; //$NON-NLS-1$
	private static final String PROPERTY_VALUE_ATTR = "value"; //$NON-NLS-1$

	private StreamDescriber[] streamDescribers;
	private Map<String, IConfigurationElement> eventProviders;
	private Map<String, IConfigurationElement> snapshotProviders;
	private Map<String, IConfigurationElement> transportProviders;
	private Map<String, IConfigurationElement> eventConfigurations;
	private Map<String, IConfigurationElement> snapshotConfigurations;
	private Map<String, IConfigurationElement> transportConfigurations;
	private Map<IConfigurationElement, IConfiguration> configurations = new HashMap<IConfigurationElement, IConfiguration>();

	public StreamDescriber[] getStreams() {
		if (streamDescribers == null) {
			IConfigurationElement[] streams = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(STREAMS);
			streamDescribers = new StreamDescriber[streams.length];
			for (int i = 0; i < streams.length; i++) {
				streamDescribers[i] = loadStream(streams[i]);
			}
		}
		return streamDescribers;
	}

	private StreamDescriber loadStream(IConfigurationElement element) {
		IConfigurationElement[] events = element
				.getChildren(STREAM_EVENT_ELEMENT);
		EventDescriber[] eventDescribers = new EventDescriber[events.length];
		for (int i = 0; i < events.length; i++) {
			eventDescribers[i] = loadStreamEvent(events[i]);
		}
		IConfigurationElement[] transports = element
				.getChildren(STREAM_TRANSPORT_ELEMENT);
		TransportDescriber[] transportDescribers = new TransportDescriber[transports.length];
		for (int i = 0; i < transports.length; i++) {
			transportDescribers[i] = loadStreamTransport(transports[i]);
		}
		return new StreamDescriber(eventDescribers, transportDescribers);
	}

	private EventDescriber loadStreamEvent(IConfigurationElement element) {
		String providerId = element
				.getAttribute(CONFIGURATION_PROVIDER_ID_ATTR);
		IConfiguration configuration = createConfiguration(element,
				EventConfigurationHelper);
		IConfigurationElement[] snapshots = element
				.getChildren(CONFIGURATION_SNAPSHOT_ELEMENT);
		SnapshotDescriber[] snapshotDescribers = new SnapshotDescriber[snapshots.length];
		for (int i = 0; i < snapshots.length; i++) {
			snapshotDescribers[i] = loadSnapshot(snapshots[i]);
		}
		return new EventDescriber(providerId, configuration, snapshotDescribers);
	}

	private SnapshotDescriber loadSnapshot(IConfigurationElement element) {
		String providerId = element
				.getAttribute(CONFIGURATION_PROVIDER_ID_ATTR);
		IConfiguration configuration = createConfiguration(element,
				SnapshotConfigurationHelper);
		return new SnapshotDescriber(providerId, configuration);
	}

	private TransportDescriber loadStreamTransport(IConfigurationElement element) {
		String providerId = element
				.getAttribute(CONFIGURATION_PROVIDER_ID_ATTR);
		IConfiguration configuration = createConfiguration(element,
				TransportConfigurationHelper);
		return new TransportDescriber(providerId, configuration);
	}

	public IEventProvider createEventProvider(String id) {
		checkIdNotNull(id);
		if (eventProviders == null) {
			loadEvents();
		}
		return doCreateProvider(id, eventProviders.get(id));
	}

	public ISnapshotProvider createSnapshotProvider(String id) {
		checkIdNotNull(id);
		if (snapshotProviders == null) {
			loadSnapshots();
		}
		return doCreateProvider(id, snapshotProviders.get(id));
	}

	public ITransportProvider createTransportProvider(String id) {
		checkIdNotNull(id);
		if (transportProviders == null) {
			loadTransports();
		}
		return doCreateProvider(id, transportProviders.get(id));
	}

	private void checkIdNotNull(String id) {
		Assert.isLegal(id != null, "Provider id must not be null");
	}

	@SuppressWarnings("unchecked")
	private <P extends IProvider> P doCreateProvider(String id,
			IConfigurationElement element) {
		if (element == null)
			throw new IllegalArgumentException(NLS.bind(
					"Provider {0} not found.", id));
		try {
			return (P) element.createExecutableExtension(PROVIDER_CLASS_ATTR);
		} catch (CoreException e) {
			throw new IllegalArgumentException(NLS.bind(
					"Failed to instantiate provider {0}", id), e);
		}
	}

	private IConfiguration getConfiguration(String id,
			ConfigurationHelper helper) {
		if (helper.getConfigurationMap() == null) {
			helper.load();
		}
		IConfigurationElement element = helper.getConfigurationMap().get(id);
		if (element == null) {
			throw new IllegalArgumentException(NLS.bind(
					"Configuration {0} not found", id));
		}
		IConfiguration configuration = configurations.get(element);
		if (configuration == null) {
			configuration = createConfiguration(element, helper);
			configurations.put(element, configuration);
		}
		return configuration;
	}

	private IConfiguration createConfiguration(IConfigurationElement element,
			ConfigurationHelper helper) {
		String baseId = element.getAttribute(CONFIGURATION_BASE_ID_ATTR);
		IConfiguration base = null;
		if (baseId != null) {
			checkForDependencyCircles(
					element.getAttribute(CONFIGURATION_ID_ATTR), baseId);
			base = getConfiguration(baseId, helper);
			String providerId = element
					.getAttribute(CONFIGURATION_PROVIDER_ID_ATTR);
			checkProvider(base, providerId);
		}
		IConfiguration configuration = new Configuration(base,
				loadProperties(element));
		return configuration;
	}

	private void checkForDependencyCircles(String id, String baseId) {
		// TODO Auto-generated method stub
		if (id == null || baseId == null)
			return;
	}

	private void checkProvider(IConfiguration c, String providerId) {
		// TODO Auto-generated method stub

	}

	private Map<String, String> loadProperties(IConfigurationElement element) {
		Map<String, String> properties = new HashMap<String, String>();
		for (IConfigurationElement p : element
				.getChildren(CONFIGURATION_PROPERTY_ELEMENT)) {
			String name = p.getAttribute(PROPERTY_NAME_ATTR);
			String value = p.getAttribute(PROPERTY_VALUE_ATTR);
			if (properties.containsKey(name)) {
				logDuplicatePropertyName(p, name);
			} else {
				properties.put(name, value);
			}
		}
		return properties;
	}

	private void loadEvents() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EVENTS);
		eventProviders = new HashMap<String, IConfigurationElement>();
		eventConfigurations = new HashMap<String, IConfigurationElement>();
		loadContributions(elements, eventProviders, eventConfigurations);
	}

	private void loadSnapshots() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(SNAPSHOTS);
		snapshotProviders = new HashMap<String, IConfigurationElement>();
		snapshotConfigurations = new HashMap<String, IConfigurationElement>();
		loadContributions(elements, snapshotProviders, snapshotConfigurations);
	}

	private void loadTransports() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(TRANSPORTS);
		transportProviders = new HashMap<String, IConfigurationElement>();
		transportConfigurations = new HashMap<String, IConfigurationElement>();
		loadContributions(elements, transportProviders, transportConfigurations);
	}

	private void loadContributions(IConfigurationElement[] elements,
			Map<String, IConfigurationElement> providers,
			Map<String, IConfigurationElement> configurations) {
		for (IConfigurationElement e : elements) {
			if (PROVIDER_ELEMENT.equals(e.getName())) {
				String id = e.getAttribute(PROVIDER_ID_ATTR);
				if (providers.containsKey(id)) {
					logDuplicateId(e, id);
				} else {
					providers.put(id, e);
				}
			}
			if (CONFIGURATION_ELEMENT.equals(e.getName())) {
				String id = e.getAttribute(CONFIGURATION_ID_ATTR);
				if (configurations.containsKey(id)) {
					logDuplicateId(e, id);
				} else {
					configurations.put(id, e);
				}
			}
		}
	}

	// TODO log contributor id
	private void logDuplicateId(IConfigurationElement e, String id) {
		Activator.logError(NLS.bind("Duplicate id: {0}. "
				+ "Contribution is ignored", id));
	}

	// TODO log contributor id
	private void logDuplicatePropertyName(IConfigurationElement e, String name) {
		Activator.logError(NLS.bind("Duplicate property name: {0}. "
				+ "Property is ignored", name));
	}

	private interface ConfigurationHelper {
		Map<String, IConfigurationElement> getConfigurationMap();

		void load();
	}

	private final ConfigurationHelper EventConfigurationHelper = new ConfigurationHelper() {
		@Override
		public Map<String, IConfigurationElement> getConfigurationMap() {
			return eventConfigurations;
		}

		@Override
		public void load() {
			loadEvents();
		}
	};

	private final ConfigurationHelper SnapshotConfigurationHelper = new ConfigurationHelper() {
		@Override
		public Map<String, IConfigurationElement> getConfigurationMap() {
			return snapshotConfigurations;
		}

		@Override
		public void load() {
			loadSnapshots();
		}
	};

	private final ConfigurationHelper TransportConfigurationHelper = new ConfigurationHelper() {
		@Override
		public Map<String, IConfigurationElement> getConfigurationMap() {
			return transportConfigurations;
		}

		@Override
		public void load() {
			loadTransports();
		}
	};

}
