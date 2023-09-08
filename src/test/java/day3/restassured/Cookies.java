package day3.restassured;

import static io.restassured.RestAssured.when;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class Cookies {

	//@Test
	void cookie() {
		
		when()
			.get("https://www.google.com")
			
		.then()
			.statusCode(200)
			.cookie("AEC")
			.log().all();

	}
	
	@Test
	void getAllcookie() {
		
		//Response res = given()
		
		Response res = when()
			.get("https://www.google.com");
		
		Map<String,String> cookies_values = res.getCookies();
		
		System.out.println(cookies_values.keySet());
	}
}
