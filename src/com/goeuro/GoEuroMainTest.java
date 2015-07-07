package com.goeuro;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Tests for the GoEuroMain class.
 */
public class GoEuroMainTest {
	
	private JSONObject jsonObj1;
	private JSONObject jsonObj2;
	
	@BeforeTest
	private void setup(){
		JSONObject geoPosition;
		
		jsonObj1 = new JSONObject();
		jsonObj1.put("_id", 376217);
		jsonObj1.put("key", JSONObject.NULL);
		jsonObj1.put("name", "Berlin");
		jsonObj1.put("fullName", "Berlin, Germany");
		jsonObj1.put("iata_airport_code", JSONObject.NULL);
		jsonObj1.put("type", "location");
		jsonObj1.put("country", "Germany");
		geoPosition = new JSONObject();
		geoPosition.put("latitude", 52.52437);
		geoPosition.put("longitude", 13.41053);
		jsonObj1.put("geo_position", geoPosition);
		jsonObj1.put("locationId", 8384);
		jsonObj1.put("inEurope", true);
		jsonObj1.put("countryCode", "DE");
		jsonObj1.put("coreCountry", true);
		jsonObj1.put("distance", JSONObject.NULL);
	
		jsonObj2 = new JSONObject();
		jsonObj2.put("_id", 393496);
		jsonObj2.put("key", JSONObject.NULL);
		jsonObj2.put("name", "Bergen");
		jsonObj2.put("fullName", "Bergen, Norway");
		jsonObj2.put("iata_airport_code", JSONObject.NULL);
		jsonObj2.put("type", "location");
		jsonObj2.put("country", "Norway");
		geoPosition = new JSONObject();
		geoPosition.put("latitude", 60.39292907714844);
		geoPosition.put("longitude", 5.324578762054443);
		jsonObj2.put("geo_position", geoPosition);
		jsonObj2.put("locationId", 25708);
		jsonObj2.put("inEurope", true);
		jsonObj2.put("countryCode", "NO");
		jsonObj2.put("coreCountry", false);
		jsonObj2.put("distance", JSONObject.NULL);		
	}
	
	@Test
	public void testGetOutputString1() {
		String result = GoEuroMain.getOutputString(null);
		
		Assert.assertEquals(result, "");
	}
	
	@Test
	public void testGetOutputString2() {
		JSONArray jsonArray = new JSONArray();
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, "");
	}
	
	@Test
	public void testGetOutputString3() {
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(jsonObj1);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, "376217,Berlin,location,52.52437,13.41053");
	}
	
	@Test
	public void testGetOutputString4() {
		JSONObject emptyJsonObject = new JSONObject();
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(emptyJsonObject);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, ",,,,");
	}
	
	@Test
	public void testGetOutputString5() {
		JSONObject nullJsonObject = new JSONObject();
		nullJsonObject.put("_id", JSONObject.NULL);
		nullJsonObject.put("key", JSONObject.NULL);
		nullJsonObject.put("name", JSONObject.NULL);
		nullJsonObject.put("fullName", JSONObject.NULL);
		nullJsonObject.put("iata_airport_code", JSONObject.NULL);
		nullJsonObject.put("type", JSONObject.NULL);
		nullJsonObject.put("country", JSONObject.NULL);
		nullJsonObject.put("geo_position", JSONObject.NULL);
		nullJsonObject.put("locationId", JSONObject.NULL);
		nullJsonObject.put("inEurope", JSONObject.NULL);
		nullJsonObject.put("countryCode", JSONObject.NULL);
		nullJsonObject.put("coreCountry", JSONObject.NULL);
		nullJsonObject.put("distance", JSONObject.NULL);
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(nullJsonObject);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, ",,,,");
	}
	
	@Test
	public void testGetOutputString6() {
		JSONObject jsonObjectWithNullGeoPos = new JSONObject();
		JSONObject nullGeoPosition = new JSONObject();
		nullGeoPosition.put("latitude", JSONObject.NULL);
		nullGeoPosition.put("longitude", JSONObject.NULL);
		jsonObjectWithNullGeoPos.put("geo_position", JSONObject.NULL);

		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(jsonObjectWithNullGeoPos);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, ",,,,");
	}
	
	@Test
	public void testGetOutputString7() {
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(jsonObj1);
		jsonObjects.add(jsonObj2);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, 
				"376217,Berlin,location,52.52437,13.41053\n393496,Bergen,location,60.39292907714844,5.324578762054443");
	}
	
	@Test
	public void testGetOutputString8() {
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		jsonObjects.add(jsonObj2);
		jsonObjects.add(jsonObj1);
		
		JSONArray jsonArray = new JSONArray(jsonObjects);
		String result = GoEuroMain.getOutputString(jsonArray);
		
		Assert.assertEquals(result, 
				"393496,Bergen,location,60.39292907714844,5.324578762054443\n376217,Berlin,location,52.52437,13.41053");
	}

}
