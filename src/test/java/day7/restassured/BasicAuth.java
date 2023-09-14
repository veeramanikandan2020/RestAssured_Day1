package day7.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BasicAuth {
	
	//@Test
	void testBasicAuth()
	{
	
		given()
			.auth().basic("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test
	void testDigestAuth()
	{
	
		given()
			.auth().digest("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
	//@Test
	void testPreemptiveAuth()
	{
	
		given()
			.auth().preemptive().basic("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testBearerToken() {
		
		String bearerToken = "Bearer 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";
		
		given()
		
		.header("Authorization",bearerToken)
		
		.when()
		
			.get("https://github.com/veeramanikandan2020/RestAssured_Day1")
		
		.then()
		
			.statusCode(200)
		
			.log().all();

	}

}
