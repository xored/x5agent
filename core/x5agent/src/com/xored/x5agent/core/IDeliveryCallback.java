package com.xored.x5agent.core;

public interface IDeliveryCallback {
	void accepted(String id);

	void wontAccept(String id, String reason);

	void retry(String id, String reason);
}
