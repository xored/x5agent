package com.xored.x5server.core;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface X5RequestHandler {

	X5Response handle(X5Request request);

}
