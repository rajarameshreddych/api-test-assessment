package com.apple.itunestests;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.apple.itunes.SearchParameters;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchApiTests extends BaseTest {

	private final String END_POINT = "/search";

	@Test(dataProvider = "testDataForsearchApiTestsWithDifferentCombinations")
	public void searchApiTestsWithDifferentCombinations(Map<String, String> testData,
			Map<String, String> expectedResults) {
		Response response = invokeSearch(END_POINT, testData);

		String jsonResponseAsString = response.asString();

		JSONObject jsonObject = new JSONObject(jsonResponseAsString);

		JSONArray resultsArray = jsonObject.getJSONArray("results");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected a 200 status code");

		if (expectedResults.containsKey("ExpectedResultCount"))
			Assert.assertEquals(jsonObject.getInt("resultCount"),
					Integer.parseInt(expectedResults.get("ExpectedResultCount")), "Result count is not as expected");

		if (expectedResults.containsKey("kind")) {
			for (int i = 0; i < resultsArray.length(); i++) {
				JSONObject entry = resultsArray.getJSONObject(i);
				String kindValue = entry.getString("kind");
				Assert.assertEquals(kindValue, expectedResults.get("kind"), "Entry " + i + " does not have kind=music-video");
			}
		}

		// We can have more validations for different test cases.
		// And We can move all Assertions to one method like checkAssertions and
		// complete all there.
		// So test looks simpler
	}
	
	@Test(dataProvider = "testDataForSearchWithInvalidData")
	public void searchWithInvalidData(Map<String, String> testData, Map<String, String> expectedResults) {
		Response response = invokeSearch(END_POINT, testData);

		Assert.assertEquals(response.getStatusCode(), 400, "Expected a 400 Bad Request status code");

		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains(expectedResults.get("ErrorMessage")),
				"Expected error message not found in response body");
	}
	
	@Test
	public void testWithMissingTermParameter() {
		Response response = RestAssured.given().baseUri(BASE_URL).when().get(END_POINT);

		Assert.assertEquals(response.getStatusCode(), 400, "Expected a 400 Bad Request status code");
	}

	@DataProvider(name = "testDataForsearchApiTestsWithDifferentCombinations")
	public Object[][] testDataForsearchApiTestsWithDifferentCombinations() {
		SearchParameters params1 = new SearchParameters("Jack+Johnson", "", "", "", "", "", "", "", "", "");
		Map<String, String> ExpectedResults1 = new HashMap<String, String>();
		ExpectedResults1.put("ExpectedResultCount", "50");

		SearchParameters params2 = new SearchParameters("Jack+Johnson", "", "", "", "", "", "1", "", "", "");
		Map<String, String> ExpectedResults2 = new HashMap<String, String>();
		ExpectedResults2.put("ExpectedResultCount", "1");

		SearchParameters params3 = new SearchParameters("Jack+Johnson", "", "", "musicVideo", "", "", "", "", "", "");
		Map<String, String> ExpectedResults3 = new HashMap<String, String>();
		ExpectedResults3.put("ExpectedResultCount", "15");
		ExpectedResults3.put("kind", "music-video");

		SearchParameters params4 = new SearchParameters("Jack+Johnson", "", "", "musicVideo", "", "", "15", "", "", "");
		Map<String, String> ExpectedResults4 = new HashMap<String, String>();
		ExpectedResults4.put("ExpectedResultCount", "15");

		return new Object[][] { { prepareQueryParams(params1), ExpectedResults1 },
				{ prepareQueryParams(params2), ExpectedResults2 }, { prepareQueryParams(params3), ExpectedResults3 },
				{ prepareQueryParams(params4), ExpectedResults4 } };
	}

	@DataProvider(name = "testDataForSearchWithInvalidData")
	public Object[][] testDataForSearchWithInvalidData() {
		SearchParameters params1 = new SearchParameters("Jack+Johnson", "", "", "", "", "", "", "raja", "", "");
		Map<String, String> ExpectedResults1 = new HashMap<String, String>();
		ExpectedResults1.put("ErrorMessage", "Invalid value(s) for key(s): [language]");

		SearchParameters params2 = new SearchParameters("Jack+Johnson", "raja", "", "", "", "", "", "en_us", "", "");
		Map<String, String> ExpectedResults2 = new HashMap<String, String>();
		ExpectedResults2.put("ErrorMessage", "Invalid value(s) for key(s): [country]");

		SearchParameters params3 = new SearchParameters("Jack+Johnson", "", "", "raja", "", "", "", "", "", "");
		Map<String, String> ExpectedResults3 = new HashMap<String, String>();
		ExpectedResults3.put("ErrorMessage", "Invalid value(s) for key(s): [resultEntity]");

		SearchParameters params4 = new SearchParameters("Jack+Johnson", "", "raja", "", "", "", "", "", "", "");
		Map<String, String> ExpectedResults4 = new HashMap<String, String>();
		ExpectedResults4.put("ErrorMessage", "Invalid value(s) for key(s): [mediaType]");

		return new Object[][] { { prepareQueryParams(params1), ExpectedResults1 },
				{ prepareQueryParams(params2), ExpectedResults2 }, { prepareQueryParams(params3), ExpectedResults3 },
				{ prepareQueryParams(params4), ExpectedResults4 } };
	}

	private Map<String, String> prepareQueryParams(SearchParameters params) {
		Map<String, String> queryParams = new HashMap<>();
		
		if (params.getTerm() != null && !params.getTerm().isEmpty()) {
			queryParams.put("term", params.getTerm());
		}
		if (params.getCountry() != null && !params.getCountry().isEmpty()) {
			queryParams.put("country", params.getCountry());
		}
		if (params.getMedia() != null && !params.getMedia().isEmpty()) {
			queryParams.put("media", params.getMedia());
		}
		if (params.getEntity() != null && !params.getEntity().isEmpty()) {
			queryParams.put("entity", params.getEntity());
		}
		if (params.getAttribute() != null && !params.getAttribute().isEmpty()) {
			queryParams.put("attribute", params.getAttribute());
		}
		if (params.getCallback() != null && !params.getCallback().isEmpty()) {
			queryParams.put("callback", params.getCallback());
		}
		if (params.getLimit() != null && !params.getLimit().isEmpty()) {
			queryParams.put("limit", params.getLimit());
		}
		if (params.getLang() != null && !params.getLang().isEmpty()) {
			queryParams.put("lang", params.getLang());
		}
		if (params.getVersion() != null && !params.getVersion().isEmpty()) {
			queryParams.put("version", params.getVersion());
		}
		if (params.getExplicit() != null && !params.getExplicit().isEmpty()) {
			queryParams.put("explicit", params.getExplicit());
		}

		return queryParams;
	}

}
