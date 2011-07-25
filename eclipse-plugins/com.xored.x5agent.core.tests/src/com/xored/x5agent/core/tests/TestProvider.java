package com.xored.x5agent.core.tests;

import java.util.Map;
import java.util.Map.Entry;

public class TestProvider {

	public void initialize(Map<String, String> parameters) {
		System.out.println(getClass().getName() + " initializng");
		for (Entry<String, String> e : parameters.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}

	}

	public void dispose() {
		System.out.println(getClass().getName() + " disposing");
	}

}
