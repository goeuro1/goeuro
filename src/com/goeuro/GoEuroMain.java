package com.goeuro;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The main class for the GoEuro problem set. This class contains the
 * main method which will be run at execution time.
 */
public class GoEuroMain {
	
	private static final Logger log = Logger.getLogger(GoEuroMain.class.getName());

	private static final String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	/**
	 * Get the usage instructions for this program.
	 * 
	 * @return a string containing the usage instructions
	 */
	private static String getUsageString() {
		StringBuilder str = new StringBuilder();
		str.append("Usage: \n");
		str.append("\tThis is a single argument program.\n");
		str.append("\tPlease provide 1 argument[ARG] to be used to query the following url.\n");
		str.append("\thttp://api.goeuro.com/api/v2/position/suggest/en/ARG\n");
		return str.toString();
	}
	
	/**
	 * Generate the required output {@code String} as specified
	 * by the GoEuro problem set.<br/><br/>
	 * 
	 * For each json object in the array output a line formatted as:
	 *  
	 *     _id,name,type,latitude,longitude
	 * 
	 * @param jsonArray the json array to be used to generate
	 *                  the output string.
	 * @return a properly formatted String containing the required values
	 *         or an empty String.
	 */
	protected static String getOutputString(JSONArray jsonArray) {
		GoEuroUtils utils = new GoEuroUtils();
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; jsonArray != null && i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.optJSONObject(i);
			JSONObject geoPositionObject = jsonObject != null ? 
					jsonObject.optJSONObject("geo_position") : null;
					
			String id = utils.safeOptString(jsonObject, "_id", "");
			String name = utils.safeOptString(jsonObject, "name", "");
			String type = utils.safeOptString(jsonObject, "type", "");
			String latitude = utils.safeOptString(geoPositionObject, "latitude", "");
			String longitude = utils.safeOptString(geoPositionObject, "longitude", "");
		
			stringBuilder.append(id);
			stringBuilder.append(",");
			stringBuilder.append(name);
			stringBuilder.append(",");
			stringBuilder.append(type);
			stringBuilder.append(",");
			stringBuilder.append(latitude);
			stringBuilder.append(",");
			stringBuilder.append(longitude);
			
			if (i+1 < jsonArray.length()) {
				stringBuilder.append("\n");
			}		
		}
		
		return stringBuilder.toString();
	}

	/**
	 * Main method to be run by users of the jar. The result is
	 * is printed to standard out.
	 * 
	 * @param args an array which should have exactly one element,
	 *             the string to query they GoEuro API with.
	 * @throws IOException if there is a problem doing IO.
	 */
	public static void main(String[] args) throws IOException {
		
		// if output need to go somewhere else just generate
		// an OutputStream to write to and wrap it with the BufferedWriter.
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
			
			String outputString = getOutputString(jsonArray);
			writer.write(outputString);
		}

		writer.close();
	}

}
