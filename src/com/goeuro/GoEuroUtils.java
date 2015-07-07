package com.goeuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONObject;

/**
 * A class containing utility methods.
 */
public final class GoEuroUtils {
	
	private static final Logger log = Logger.getLogger(GoEuroUtils.class.getName());
	
	/**
	 * Utility method for extracting optional values as a {@code String} 
	 * from a {@code JSONObject}.
	 * 
	 * @param jsonObject   the jsonObject to query
	 * @param key          the key to lookup
	 * @param defaultValue the value to return if the jsonObject is null 
	 * 					   or has no associated value for the given key
	 * @return the value associated with the key as a String or the defaultValue
	 */
	public String safeOptString(JSONObject jsonObject, String key, String defaultValue) {
		String value = jsonObject != null && jsonObject.has(key) ? 
				jsonObject.optString(key, defaultValue) : defaultValue;
				
		return value;
	}
	
	/**
	 * Does a get request to the provided url.
	 * 
	 * @param url a url string.
	 * @return the result of a get request on the provided url.
	 * @throws MalformedURLException if there was a problem with the input url.
	 * @throws IOException if an I/O error occurs.
	 * @see URL
	 */
	public String get(String url) throws MalformedURLException, IOException {
		final StringBuilder response = new StringBuilder();
		
		// Set up variable which may need closing
		HttpURLConnection con = null;
		InputStream conInputStream = null;
		
		URL urlObj = new URL(url);
		
		try {
			
			con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			conInputStream = con.getInputStream();
		
			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(conInputStream))) {
				String inputLine; 
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}
			
		} finally {
			if (conInputStream != null) { conInputStream.close(); }
		}
		
		return response.toString();
	}

}
