package com.javacodegeeks.junitrestweb;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void testUserFetchesSuccess() throws ParseException {
		Response serviceResponse = get("/country/get/all");
		Assert.assertEquals(serviceResponse.getStatusCode(), 200);
		serviceResponse.then()
		.body("RestResponse.messages[0]", equalTo("Total [249] records found."));
		int length = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result.length()");
		System.out.println(length);
		List<String> names = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].name");
		System.out.println(names);
		List<String> alpha2 = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].alpha2_code");
		System.out.println(alpha2);
		List<String> alpha3 = JsonPath.read(serviceResponse.getBody().asString(), "$.RestResponse.result[*].alpha3_code");
		System.out.println(alpha3);
		//.body("firstName", equalTo("Vinod"))
		//.body("lastName", equalTo("Kashyap"))
		//.body("designation", equalTo("CEO"));
	}
}
