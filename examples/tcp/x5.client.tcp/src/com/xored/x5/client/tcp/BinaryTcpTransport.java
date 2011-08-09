package com.xored.x5.client.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Map;

import com.xored.x5agent.core.X5Transport;
import com.xored.x5.X5Request;
import com.xored.x5.X5Response;
import com.xored.x5.shared.tcp.TcpUtil;

public class BinaryTcpTransport implements X5Transport {
	private SocketAddress addr;
	private int timeout;

	private static final String HOST_KEY = "host";
	private static final String PORT_KEY = "port";
	private static final String TIMEOUT_KEY = "timeout";
	private static final int DEFAULT_TIMEOUT = 5000;

	private static final String WRONG_PARAM = "Invalid value for parameter %s: %s";

	@Override
	public void initialize(Map<String, String> parameters) {
		if (!parameters.containsKey(HOST_KEY)) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					HOST_KEY, "value not specified"));
		}
		if (!parameters.containsKey(PORT_KEY)) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					PORT_KEY, "value not specified"));
		}

		String portStr = parameters.get(PORT_KEY);
		String hostStr = parameters.get(HOST_KEY);
		String timeoutStr = parameters.get(TIMEOUT_KEY);
		InetAddress host;
		int port = 0;
		int timeout = DEFAULT_TIMEOUT;
		try {
			host = InetAddress.getByName(hostStr);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					HOST_KEY, e.getMessage()));
		}

		try {
			port = Integer.parseInt(portStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					PORT_KEY,
					String.format("Cannot parse %s as integer", portStr)));
		}

		if (port < 0) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					PORT_KEY, "Port cannot be less than zero"));
		}

		if (timeoutStr != null) {
			try {
				timeout = Integer.parseInt(timeoutStr);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						String.format(WRONG_PARAM, PORT_KEY, String.format(
								"Cannot parse %s as integer", timeoutStr)));
			}
		}

		if (timeout < 0) {
			throw new IllegalArgumentException(String.format(WRONG_PARAM,
					PORT_KEY, "Port cannot be less than zero"));
		}

		this.addr = new InetSocketAddress(host, port);
	}

	@Override
	public X5Response send(X5Request request) throws IOException {
		Socket socket = new Socket();
		X5Response result;
		try {
			socket.connect(addr, timeout);
			TcpUtil.emfToSocket(socket, request);
			result = (X5Response) TcpUtil.emfFromSocket(socket);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	@Override
	public void dispose() {
	}

}
