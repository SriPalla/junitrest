package com.javacodegeeks.junitrestweb;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.javacodegeeks.junitrestweb.api.CountryCodeResponse;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CountryCodeRestTest {
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://services.groupkt.com";
		RestAssured.port = 80;
	}

	@Test
	public void testJsonUsingParser() throws ParseException {
		Response serviceResponse = get("/country/get/all");
		Assert.assertEquals(serviceResponse.getStatusCode(), 200);
		serviceResponse.then()
		.body("RestResponse.messages[0]", equalTo("Total [249] records found."));
		int length = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result.length()");
		System.out.println(length);
		System.out.println("Sample");
		System.out.println("Hello");
		List<String> names = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].name");
		System.out.println(names);
		System.out.println();
		List<String> alpha2 = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].alpha2_code");
		System.out.println(alpha2);
		System.out.println();
		List<String> alpha3 = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].alpha3_code");
		System.out.println(alpha3);
		System.out.println();
	}

	@Test
	public void testJsonUsingJaxb() throws ParseException {
		Response serviceResponse = get("/country/get/all");
		Assert.assertEquals(serviceResponse.getStatusCode(), 200);
		Gson gson = new Gson();
		CountryCodeResponse response = gson.fromJson(serviceResponse.getBody().asString(), CountryCodeResponse.class);
		System.out.println(response.getRestResponse().getMessages());
	}
}
