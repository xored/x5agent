package com.xored.x5agent.http;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncPost;
import org.apache.http.nio.reactor.IOReactorException;

import com.xored.x5agent.core.DeliveryCallback;
import com.xored.x5agent.core.Message;
import com.xored.x5agent.core.Transport;
import com.xored.x5agent.core.X5Agent;

public class HttpTransport implements Transport {

	private final class ResponseConsumer extends
			AsyncCharConsumer<HttpResponse> {

		private final String id;
		private HttpResponse response;

		// private String content;

		public ResponseConsumer(String id) {
			this.id = id;
		}

		@Override
		protected HttpResponse buildResult() throws Exception {
			return response;
		}

		@Override
		protected void onCleanup() {
		}

		@Override
		public synchronized void responseCompleted() {
			super.responseCompleted();
			StatusLine line = response.getStatusLine();
			int statusCode = line.getStatusCode();
			String reason = line.getReasonPhrase();
			if (statusCode == HttpStatus.SC_CREATED) {
				X5Agent.Instance.getLog().info("accepted: " + id);
				callback.accepted(id);
			} else if (statusCode >= 500 && statusCode < 600) {
				X5Agent.Instance.getLog().info(
						"retry: " + id + ",\nreason: " + reason);
				callback.retry(id, reason);
			} else {
				X5Agent.Instance.getLog().info(
						"wontAccept: " + id + ",\nreason: " + reason);
				callback.wontAccept(id, reason);
			}
		}

		@Override
		protected void onResponseReceived(HttpResponse response) {
			this.response = response;
		}

		@Override
		protected void onCharReceived(CharBuffer buf, IOControl ioctrl)
				throws IOException {
			X5Agent.Instance.getLog().info(buf.toString());
			// this.content = buf.toString();
		}
	}

	private DeliveryCallback callback;
	private HttpAsyncClient client = null;

	// private String url;

	@Override
	public void initialize(Map<String, String> parameters,
			DeliveryCallback callback) {
		// this.url = parameters.get("url");
		this.callback = callback;
		try {
			this.client = new DefaultHttpAsyncClient();
		} catch (IOReactorException e) {
			throw new RuntimeException(e);
		}
		this.client.start();
	}

	@Override
	public void send(Message[] messages) {
		String url = "http://localhost:8080/api2/xored-test/facts/";
		for (Message m : messages) {
			HttpAsyncPost post = new HttpAsyncPost(url, m.getBody(),
					"application/json", "utf-8");
			client.execute(post, new ResponseConsumer(m.getId()), null);
			X5Agent.Instance.getLog().info("posted: " + m.getId());
		}
	}

	@Override
	public void dispose() {
		if (client != null) {
			try {
				client.shutdown();
			} catch (InterruptedException e) {
				Thread.interrupted(); // restore state
			}
		}
	}
}
