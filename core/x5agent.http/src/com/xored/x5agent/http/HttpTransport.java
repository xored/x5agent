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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.eclipse.emf.ecore.EObject;

import com.xored.x5.DeliveryStatus;
import com.xored.x5.EmfSerializer;
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
	}

	@Override
	public X5Response send(X5Request request) {
		EmfSerializer emfSerializer = new EmfSerializer();
		try {
			HttpClient client = new DefaultHttpClient();
			String url = this.baseUrl + "/" + X5Agent.Instance.getClient()
					+ "/facts/";
			HttpPost post = new HttpPost(url);
			NStringEntity entity = new NStringEntity(
					emfSerializer.eObjectToJson(request), "utf-8");
			entity.setContentType("application/json" + HTTP.CHARSET_PARAM
					+ "utf-8");
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
				EObject eObject = emfSerializer.jsonToEObject(json);
				if (eObject instanceof X5Response) {
					return (X5Response) eObject;
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
