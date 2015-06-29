package com.goeuro;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoEuroMain {
	
	private static final Logger log = Logger.getLogger(GoEuroMain.class.getName());

	private static final String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	private static String getUsageString() {
		StringBuilder str = new StringBuilder();
		str.append("Usage: \n");
		str.append("\tThis is a single argument program.\n");
		str.append("\tPlease provide 1 argument[ARG] to be used to query the following url.\n");
		str.append("\thttp://api.goeuro.com/api/v2/position/suggest/en/ARG\n");
		return str.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(System.out));

		if (args.length != 1) {
			writer.write(getUsageString());
			writer.close();
			return;
		}

		GoEuroUtils utils = new GoEuroUtils();
		String url = BASE_URL + args[0];
		String jsonString = utils.get(url).trim();

		if (jsonString.length() > 0 && jsonString.startsWith("[")
				&& jsonString.endsWith("]")) {

			JSONArray jsonArray = null;
			
			try {
				jsonArray = new JSONArray(utils.get(url));
			} catch (JSONException ex) { /* problem with the request, notify user? */ }
			
			for (int i = 0; jsonArray != null && i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.optJSONObject(i);
				JSONObject geoPositionObject = jsonObject != null ? 
						jsonObject.getJSONObject("geo_position") : null;
						
				String id = jsonObject != null ? Long.toString(jsonObject.getLong("_id")) : "";
				String name = jsonObject != null ? jsonObject.getString("name") : "";
				String type = jsonObject != null ? jsonObject.getString("type") : "";
				String latitude = geoPositionObject != null ? 
						Double.toString(geoPositionObject.getDouble("latitude")) : "";
				String longitude = geoPositionObject != null ?
						Double.toString(geoPositionObject.getDouble("longitude")) : "";
			
				writer.write(id);
				writer.write(",");
				writer.write(name);
				writer.write(",");
				writer.write(type);
				writer.write(",");
				writer.write(latitude);
				writer.write(",");
				writer.write(longitude);
				writer.write("\n");
				
			}
		}

		writer.close();
	}

}
