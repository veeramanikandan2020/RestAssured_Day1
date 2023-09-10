package day3.restassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class Headers {
	
	@Test
	void headers() {
		
		given()
		
		.when()
		
			.get("https://www.google.com/")
			

		.then()
		
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding","gzip")
			.header("Server","gws");
	}
	
	@Test
	void allHeaders() {
		
		Response res = given()
		
		.when()
		
			.get("https://www.google.com/");
		
		//get single header
		
		String headerValue = res.getHeader("Content-Type");
		System.out.println("Header Content-Type : "+headerValue);
			

		io.restassured.http.Headers allheaders = res.getHeaders();
		
		for(Header hd : allheaders)
		{
			System.out.println(hd.getName()+ "     "+hd.getValue());
		}
	}

}
