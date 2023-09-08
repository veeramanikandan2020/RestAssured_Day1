package day1.restassured;
	
	import org.testng.annotations.Test;

	import static io.restassured.RestAssured.*;
	import static io.restassured.matcher.RestAssuredMatchers.*;
	import static org.hamcrest.Matchers.*;

import java.util.HashMap;

	public class HTTPRequest{
		
		int id ;

		@Test (priority = 1)
		void getUser() {

			when()
				.get("https://reqres.in/api/users/2")

			.then().statusCode(200)
				.log().all();

		}

		/*
		
		@Test(priority = 2)
		void listUser() {
		
		when()
			.get("https://reqres.in/api/users?page=2")

		.then().statusCode(200)
			.body("page", equalTo(2))
			.log().all();

		}*/
		
		
		
		@Test(priority = 3)
		void createUser() {
			
			HashMap<String, String> data = new HashMap<String, String>();
			
			data.put("name", "Pavan");
			data.put("Job", "Trainer");
			
			id = given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
				
			//.then()
				//.log().all();
		}
			
		@Test(priority = 4, dependsOnMethods = "createUser")
		void updateUser() {
			
			HashMap<String, String> data = new HashMap<String, String>();
			
			data.put("name", "Pavan");
			data.put("Job", "Teacher");
			
			given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.put("https://reqres.in/api/users/"+id)
				
			.then()
				.log().all();
		}

	}


