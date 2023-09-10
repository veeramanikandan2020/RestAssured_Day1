package day4.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class ParsingJSONBody {
	
	//Approach 1
	@Test
	void testJsonResponse() {
		
		given()
		
		.when()
			.get("http://localhost:3000/books")
		
		.then()
			.statusCode(200)
			.body("books[2].Title",equalTo("Moby Dick"))
			.header("Content-Type", "application/json; charset=utf-8");

	}
	
	//Approach 2
	
	@Test
	void testJsonBodyResponse() {
		
		Response res = given()
		
		.when()
		.get("http://localhost:3000/books");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("books[2].Title").toString();
		
		Assert.assertEquals(bookname, "Moby Dick");
		

	}
	
	

}
