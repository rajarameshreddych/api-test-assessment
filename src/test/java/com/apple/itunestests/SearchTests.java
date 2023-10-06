package com.apple.itunestests;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.apple.itunes.SearchParameters;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SearchTests extends BaseTest {

	//This can be ignored. In this I tried to prepare url and validate. But not continued on this
	
	@Test
	public void sampleTestSearch() {
		String term = "jack+johnson";
		String country = "US";
		String media = "music";
		String entity = "musicTrack";
		int limit = 5;

		Response response = given().baseUri(BASE_URL).param("term", term).param("country", country)
				.param("media", media).param("entity", entity).param("limit", limit).when().get("/search");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.asString());
	}

	@Test(dataProvider = "searchParameters1")
	public void searchAPITestWithAllParametersInURL(SearchParameters searchParams, boolean expectedResult) {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		String url = prepareURL(searchParams);
		System.out.println(url);
		Response response = RestAssured.given()
				.baseUri(url)
				.when()
				.get();

		int actualResultCount = response.jsonPath().getInt("resultCount");

		System.out.println("Actual Result Count: " + actualResultCount);
		
		String responseBody = response.getBody().asString();
		
		System.out.println(responseBody);
		
		RestAssured.given()
	    .contentType("application/json")
	    .body(responseBody)
	    .then()
	    .body("resultCount", Matchers.equalTo(5));
	}

	@DataProvider(name = "searchParameters1")
	public Object[][] searchParameters1() {
		return new Object[][] {
			{ new SearchParameters("jack+johnson", "US", "", "", "", "", "5", "", "2", ""), true },
//			{ new SearchParameters("jack+johnson", "US", "", "", "", "", "5", "", "2", ""), true },
//			{ new SearchParameters("jack+johnson", "", "", "", "", "", "", "", "", ""), true }
			};
	}

	public String prepareURL(SearchParameters searchParams) {

		String term = searchParams.getTerm();
		String country = searchParams.getCountry();
		String media = searchParams.getMedia();
		String entity = searchParams.getEntity();
		String attribute = searchParams.getAttribute();
		String callback = searchParams.getCallback();
		String limit = searchParams.getLimit();
		String lang = searchParams.getLang();
		String version = searchParams.getVersion();
		String explicit = searchParams.getExplicit();

		String parametersString = BASE_URL + "/search?";
		
		parametersString += "term=" + term + "&";
		
		if (!country.isEmpty())
			parametersString += "country=" + country + "&";
		
		if (!media.isEmpty())
			parametersString += "media=" + media + "&";
		
		if (!entity.isEmpty())
			parametersString += "entity=" + entity + "&";
		
		if (!attribute.isEmpty())
			parametersString += "attribute=" + attribute + "&";
		
		if (!limit.isEmpty())
			parametersString += "limit=" + limit + "&";
		
		if (!lang.isEmpty())
			parametersString += "lang=" + lang + "&";
		
		if (!version.isEmpty())
			parametersString += "version=" + version + "&";
		
		if (!explicit.isEmpty())
			parametersString += "explicit=" + explicit + "&";
		
		if (!callback.isEmpty())
			parametersString += "callback=" + callback + "&";
		
		return parametersString;
	}

	public String prepareURL_bck(SearchParameters searchParams) {
		String parametersString = BASE_URL + "/search?" + "term=" + searchParams.getTerm() + "&" + "country="
				+ searchParams.getCountry() + "&" + "media=" + searchParams.getMedia() + "&" + "entity="
				+ searchParams.getEntity() + "&" + "attribute=" + searchParams.getAttribute() + "&" + "callback="
				+ searchParams.getCallback() + "&" + "limit=" + searchParams.getLimit() + "&" + "lang="
				+ searchParams.getLang() + "&" + "version=" + searchParams.getVersion() + "&" + "explicit="
				+ searchParams.getExplicit();

		return parametersString;
	}
}
