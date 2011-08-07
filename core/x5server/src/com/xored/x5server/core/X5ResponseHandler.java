package com.xored.x5server.core;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;

public interface X5ResponseHandler {

	X5Response handle(X5Request request);

}
