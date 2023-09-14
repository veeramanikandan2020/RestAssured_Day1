package day4.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class ParsingJSONBody {
	
	//Approach 1
	//@Test
	void testJsonResponse() {
		
		given()
		
		.when()
			.get("http://localhost:3000/book")
		
		.then()
			.statusCode(200)
			//.body("books[2].Title",equalTo("Moby Dick"))
			.header("Content-Type", "application/json; charset=utf-8");

	}
	
	//Approach 2
	
	//@Test
	void testJsonBodyResponse() {
		
		Response res = given()
		
		.when()
		.get("http://localhost:3000/book");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("books[2].Title").toString();
		
		System.out.println(bookname);
		
		//Assert.assertEquals(bookname, "Moby Dick");
		

	}
	
	//To get all books
	@Test
	void testResponseBodyData() {
		
		Response res = given()
		
		.when()
			.get("http://localhost:3000/book");
		
		//using JSON Object class
		
		JSONObject jo = new JSONObject(res.asString());
		
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("Title").toString();
			
			System.out.println(booktitle);
		}
		
		
	}
	

}
