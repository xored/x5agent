package com.xored.x5agent.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xored.emfjson.Emf2Json;
import com.xored.x5.DeliveryStatus;
import com.xored.x5.X5FactResponse;
import com.xored.x5.X5Factory;
import com.xored.x5.X5Request;
import com.xored.x5.X5Response;
import com.xored.x5agent.core.X5Agent;
import com.xored.x5agent.core.X5Transport;

public class HttpTransport implements X5Transport {

	private String baseUrl;

	@Override
	public void initialize(Map<String, String> parameters) {
		this.baseUrl = parameters.get("url");
		if (baseUrl == null)
			throw new IllegalArgumentException("URL is not specified");
	}

	@Override
	public X5Response send(X5Request request) {
		try {
			Emf2Json emf2Json = new Emf2Json();
			HttpClient client = new DefaultHttpClient();
			String url = this.baseUrl + "/" + X5Agent.Instance.getClient()
					+ "/facts/";
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(emf2Json.serialize(request)
					.toString(), "application/json", "utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity == null) {
				X5FactResponse factResponse = X5Factory.eINSTANCE
						.createX5FactResponse();
				StatusLine line = response.getStatusLine();
				int statusCode = line.getStatusCode();
				if (statusCode == HttpStatus.SC_CREATED) {
					factResponse.setStatus(DeliveryStatus.ACCEPTED);
				} else if (statusCode >= 500 && statusCode < 600) {
					factResponse.setStatus(DeliveryStatus.RETRY);
				} else {
					factResponse.setStatus(DeliveryStatus.WONT_ACCEPT);
				}
			} else {
				String json = EntityUtils.toString(responseEntity);
				JsonElement parsed = new JsonParser().parse(json);
				if (parsed instanceof JsonObject) {
					EObject eObject = emf2Json.deserialize((JsonObject) parsed);
					if (eObject instanceof X5Response) {
						return (X5Response) eObject;
					}
				}
			}
			throw new RuntimeException("Unexpected format");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void dispose() {
	}
}
