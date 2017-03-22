package com.jxnu.cic.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.widget.Toast;

public class HttpUtils {
	static HttpURLConnection conn = null;
	static InputStreamReader input = null;
	static BufferedReader buffer = null;
	static PrintWriter print=null;
	
	public static void httpconnect(String url) {
		try {
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String get() {
		String inputLine = null;
		String result = "";
		try {
			input = new InputStreamReader(conn.getInputStream());
			buffer = new BufferedReader(input);
			while ((inputLine = buffer.readLine()) != null)
				result += inputLine + "\n";
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String post(String params) {
		String result = "";
		try {
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			OutputStream out = conn.getOutputStream();
			print = new PrintWriter(out);
			print.print(params);
			print.flush();			
			InputStreamReader input = new InputStreamReader(
					conn.getInputStream());
			BufferedReader buffer = new BufferedReader(input);
			String inputLine = null;
			while ((inputLine = buffer.readLine()) != null)
				result += inputLine + "\n";
			print.close();
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void close() {
		if (conn != null)
			conn.disconnect();
	}
	
	public static String clientpost(String url, List<NameValuePair> params) {
		HttpPost httpRequest = new HttpPost(url);
		HttpEntity httpentity;
		String result = "";
		try {
			httpentity = new UrlEncodedFormEntity(params, "utf-8");
			httpRequest.setEntity(httpentity);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity());
			} else {
				result = "请求错误!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
