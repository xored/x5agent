package com.xored.x5agent.internal.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;

import com.xored.x5agent.core.IConfiguration;

public class Configuration implements IConfiguration {

	private final IConfiguration base;
	private final Map<String, String> properties;
	private Set<String> names;

	public Configuration(IConfiguration base, Map<String, String> properties) {
		Assert.isNotNull(properties);
		this.base = base;
		this.properties = properties;
	}

	@Override
	public Set<String> names() {
		if (names == null) {
			HashSet<String> all = new HashSet<String>(properties.keySet());
			if (base != null) {
				all.addAll(base.names());
			}
			names = Collections.unmodifiableSet(all);
		}
		return names;
	}

	@Override
	public String value(String key) {
		if (properties.containsKey(key)) {
			return properties.get(key);
		}
		if (base != null) {
			return base.value(key);
		}
		return null;
	}

}
