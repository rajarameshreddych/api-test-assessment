package com.apple.itunestests;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTest {
	
	public final String BASE_URL = "https://itunes.apple.com";
	
	protected Response invokeSearch(String endpoint, Map<String, String> queryParams) {
		return RestAssured.given().baseUri(BASE_URL).basePath(endpoint).queryParams(queryParams).get();
	}
	
}
