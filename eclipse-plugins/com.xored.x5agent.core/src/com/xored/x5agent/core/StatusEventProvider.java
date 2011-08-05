package com.xored.x5agent.core;


public abstract class StatusEventProvider extends AbstractX5EventProvider {

	@Override
	public String getSchema() {
		return "sherlock.eclipse_status";
	}

}
