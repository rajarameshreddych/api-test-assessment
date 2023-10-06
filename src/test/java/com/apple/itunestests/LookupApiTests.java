package com.apple.itunestests;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.apple.itunes.LookupParameters;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LookupApiTests extends BaseTest {

	private final String END_POINT = "/lookup";

	@Test(dataProvider = "testDataForlookupApiTestsWithDifferentCombinations")
	public void looupApiTestsWithDifferentCombinations(Map<String, String> testData,
			Map<String, String> expectedResults) {
		Response response = invokeSearch(END_POINT, testData);

		String jsonResponseAsString = response.asString();

		JSONObject jsonObject = new JSONObject(jsonResponseAsString);

		JSONArray resultsArray = jsonObject.getJSONArray("results");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected a 200 status code");

		if (expectedResults.containsKey("ExpectedResultCount"))
			Assert.assertEquals(jsonObject.getInt("resultCount"),
					Integer.parseInt(expectedResults.get("ExpectedResultCount")), "Result count is not as expected");

		if (expectedResults.containsKey("trackId")) {
			for (int i = 0; i < resultsArray.length(); i++) {
				JSONObject entry = resultsArray.getJSONObject(i);
				String actualValue = ""+ entry.get("trackId");
				Assert.assertEquals(actualValue, expectedResults.get("trackId"), "Entry " + i + " does not have expected trackId");
			}
		}

		// We can have more validations for different test cases.
		// And We can move all Assertions to one method like checkAssertions and
		// complete all there.
		// So test looks simpler
	}

	@Test
	public void testWithMissingTermParameter() {
		Response response = RestAssured.given().baseUri(BASE_URL).when().get(END_POINT);

		Assert.assertEquals(response.getStatusCode(), 400, "Expected a 400 Bad Request status code");
	}

	@DataProvider(name = "testDataForlookupApiTestsWithDifferentCombinations")
	public Object[][] testDataForlookupApiTestsWithDifferentCombinations() {

		LookupParameters params1 = new LookupParameters("909253", "", "");
		Map<String, String> ExpectedResults1 = new HashMap<String, String>();
		ExpectedResults1.put("ExpectedResultCount", "1");

		LookupParameters params2 = new LookupParameters("", "468749", "");
		Map<String, String> ExpectedResults2 = new HashMap<String, String>();
		ExpectedResults2.put("ExpectedResultCount", "1");

		LookupParameters params3 = new LookupParameters("", "", "9780316069359");
		Map<String, String> ExpectedResults3 = new HashMap<String, String>();
		ExpectedResults3.put("ExpectedResultCount", "1");
		ExpectedResults3.put("trackId", "395519191");

		return new Object[][] { { prepareQueryParams(params1), ExpectedResults1 },
				{ prepareQueryParams(params2), ExpectedResults2 }, { prepareQueryParams(params3), ExpectedResults3 } };
	}

	private Map<String, String> prepareQueryParams(LookupParameters params) {
		Map<String, String> queryParams = new HashMap<>();

		if (params.getId() != null && !params.getId().isEmpty()) {
			queryParams.put("id", params.getId());
		}
		if (params.getAmgArtistId() != null && !params.getAmgArtistId().isEmpty()) {
			queryParams.put("amgArtistId", params.getAmgArtistId());
		}
		if (params.getIsbn() != null && !params.getIsbn().isEmpty()) {
			queryParams.put("isbn", params.getIsbn());
		}

		return queryParams;
	}
}
