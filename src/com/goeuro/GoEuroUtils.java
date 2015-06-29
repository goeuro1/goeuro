package com.goeuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public final class GoEuroUtils {
	
	private static final Logger log = Logger.getLogger(GoEuroUtils.class.getName());
	
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
		
		// Set up buffer for return String
		final StringBuilder response = new StringBuilder();
		
		// Set up variable which may need closing
		HttpURLConnection con = null;
		InputStream conInputStream = null;
		
		// Get URL object
		URL urlObj = new URL(url);
		
		try {
			
			// Open connection
			con = (HttpURLConnection) urlObj.openConnection();
 
			// optional default is GET
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
