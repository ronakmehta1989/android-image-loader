package com.dd.util;

import com.dd.util.log.L;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HttpClient {
	public static InputStream get(String uri) {
		return get(uri, null);
	}

	public static InputStream get(String uri, UsernamePasswordCredentials credentials) {
		InputStream content = null;

		try {
			DefaultHttpClient client = new DefaultHttpClient();

			if (credentials != null) {
				client.getCredentialsProvider().setCredentials(new AuthScope(null, -1), credentials);
			}

			HttpGet request = new HttpGet(uri);
			HttpResponse response = client.execute(request);
			content = response.getEntity().getContent();

		} catch (ClientProtocolException e) {
			L.e(e.toString());
		} catch (IOException e) {
			L.e(e.toString());
		}

		return content;
	}


	public static InputStream post(String uri, List<BasicNameValuePair> nameValuePairs) {
		return post(uri, nameValuePairs, null);
	}

	public static InputStream post(String uri, List<BasicNameValuePair> nameValuePairs,
	                               UsernamePasswordCredentials credentials) {
		InputStream content = null;

		try {
			DefaultHttpClient client = new DefaultHttpClient();

			if (credentials != null) {
				client.getCredentialsProvider().setCredentials(new AuthScope(null, -1), credentials);
			}

			HttpPost request = new HttpPost(uri);

			if (nameValuePairs != null) {
				request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			}

			HttpResponse response = client.execute(request);
			content = response.getEntity().getContent();

		} catch (ClientProtocolException e) {
			L.e(e.toString());
		} catch (IOException e) {
			L.e(e.toString());
		}

		return content;
	}

}
