package day3.restassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;


public class Logs {
	
	@Test
	void logs() {
		
		given()
		
		.when()
		
			.get("https://www.google.com/")
			

		.then()
			//.log().body();
		//.log().cookies();
		//.log().headers();
		.log().all();
	}

}
