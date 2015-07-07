package com.goeuro;

import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Tests for the GoEuroUtils class.
 */
public class GoEuroUtilsTest {
	private GoEuroUtils utils;
	
	@BeforeTest
	private void setup(){
		utils = new GoEuroUtils();
	}
	
	@Test
	public void testSafeOptString1() {
		String result = utils.safeOptString(null, "key", "defaultValue");
		Assert.assertEquals(result, "defaultValue");
	}
	
	@Test
	public void testSafeOptString2() {
		JSONObject jsonObj = new JSONObject();
		
		String result = utils.safeOptString(jsonObj, "key", "defaultValue");
		Assert.assertEquals(result, "defaultValue");
	}
	
	@Test
	public void testSafeOptString3() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("key", "value");
		
		String result = utils.safeOptString(jsonObj, "key", "defaultValue");
		Assert.assertEquals(result, "value");
	}
	
	@Test
	public void testSafeOptString4() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("key", 1.2);
		
		String result = utils.safeOptString(jsonObj, "key", "defaultValue");
		Assert.assertEquals(result, "1.2");
	}
	
	@Test
	public void testGet1() {
		boolean exceptionCaught = false;
		
		try {
			String result = utils.get(null);
		} catch (MalformedURLException e) {
			exceptionCaught = true;
		} catch (Exception e) { }
		
		Assert.assertTrue(exceptionCaught);
	}
	
	@Test
	public void testGet2() {
		boolean exceptionCaught = false;
		
		try {
			String result = utils.get("asdf");
		} catch (MalformedURLException e) {
			exceptionCaught = true;
		} catch (Exception e) { }
		
		Assert.assertTrue(exceptionCaught);
	}
	
	@Test
	public void testGet3() {
		boolean exceptionCaught = false;
		String result = null;
		
		try {
			result = utils.get("http://api.goeuro.com/api/v2/position/suggest/en/asdf");
		} catch(Exception e) {
			exceptionCaught = true;
		}
		
		Assert.assertFalse(exceptionCaught);
		
		Assert.assertEquals(result, "[]");
		
		JSONArray jsonArray = new JSONArray(result);
		Assert.assertEquals(jsonArray.length(), 0);	
	}
	
	@Test
	public void testGet4() {
		boolean exceptionCaught = false;
		String result = null;
		
		try { // this will probably break at some point, what would be a better url to test?
			result = utils.get("http://api.goeuro.com/api/v2/position/suggest/en/jp");
		} catch(Exception e) {
			exceptionCaught = true;
		}
		
		Assert.assertFalse(exceptionCaught);
		
		JSONArray jsonArray = new JSONArray(result);
		Assert.assertEquals(jsonArray.length(), 1);	
	}
}
