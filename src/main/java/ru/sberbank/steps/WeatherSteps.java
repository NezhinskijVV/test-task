package ru.sberbank.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.FilenameFilter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//http://api.weatherstack.com/current?access_key=065f09178afe42f73b587ef46cc669b5&query=New%20York
public class WeatherSteps {
    private static final String KEY = "065f09178afe42f73b587ef46cc669b5";
    private static final String NEW_YORK = "New York";
    private static final String BASE_URL = "http://api.weatherstack.com/";
    private Response response;


    @When("get {string} weather")
    public void getWeather(String city) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/current" + "?access_key=" + KEY + "&"
                + "query=" + city);
        System.out.println(response.getBody().prettyPrint());
        Assert.assertEquals(200, response.getStatusCode());
        this.response = response;
    }

    @When("check location parameters {string} {string} {string}")
    public void checkWeatherLocation(String city, String country, String region) {
        System.out.println(response.prettyPrint());
        JsonPath jsonPathEvaluator = response.jsonPath();

        Map<String, String> mapParams = jsonPathEvaluator.getMap("location");

        assertAll(
                () -> assertEquals(city, mapParams.get("name")),
                () -> assertEquals(country, mapParams.get("country")),
                () -> assertEquals(region, mapParams.get("region")));
    }

    @When("check request parameters {string} {string} {string} {string}")
    public void checkWeatherLocation(String type, String query, String language, String unit) {
        JsonPath jsonPathEvaluator = response.jsonPath();

        Map<String, String> mapParams = jsonPathEvaluator.getMap("request");

        assertAll(
                () -> assertEquals(type, mapParams.get("type")),
                () -> assertEquals(query, mapParams.get("query")),
                () -> assertEquals(language, mapParams.get("language")),
                () -> assertEquals(unit, mapParams.get("unit")));
    }
}