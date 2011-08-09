package com.xored.x5.server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.xored.x5.X5Request;
import com.xored.x5.X5Response;
import com.xored.x5.shared.tcp.TcpUtil;
import com.xored.x5server.core.X5RequestHandler;
import com.xored.x5server.core.X5Transport;

public class TcpTransport implements X5Transport {
	private X5RequestHandler handler;
	private ServerSocket socket;
	private int port;
	private static final String PORT_KEY = "port";

	@Override
	public void initialize(Map<String, String> parameters,
			X5RequestHandler handler) {
		if (!parameters.containsKey(PORT_KEY)) {
			throw new IllegalArgumentException("Port is not specified");
		}

		String portStr = parameters.get(PORT_KEY);
		try {
			port = Integer.parseInt(portStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(
					"Cannot parse port value %s", portStr));
		}

		if (port < 0) {
			throw new IllegalArgumentException(
					String.format("Port value cannot be less than zero"));
		}

		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot open server socket", e);
		}
		
		//No need to store this thread:
		// once transport is disposed, socket will be closed and thread will die with IOException
		new ListenerThread().start();
	}

	@Override
	public void dispose() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	private final class ListenerThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					Socket client = socket.accept();
					new ClientThread(client).start();
				}
			} catch (IOException e) {
				// end of lifecycle
			}
		}
	}

	private final class ClientThread extends Thread {
		private Socket client;

		public ClientThread(Socket client) {
			this.client = client;
		}

		public void run() {
			try {
				X5Request request = (X5Request) TcpUtil.emfFromSocket(client);
				X5Response response = handler.handle(request);
				TcpUtil.emfToSocket(client, response);
				client.close();
			} catch (IOException e) {
				try {
					client.close();
				} catch(IOException e2) {
				}
			}
		}
	}
}
