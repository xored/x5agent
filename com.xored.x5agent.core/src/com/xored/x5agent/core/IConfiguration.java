package com.xored.x5agent.core;

import java.util.Set;

public interface IConfiguration {

	Set<String> names();

	String value(String name);
}
