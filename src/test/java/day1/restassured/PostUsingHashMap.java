package day1.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostUsingHashMap {
	
	int id = 4;

@Test		
void testPostHashMap(){
	
	HashMap data = new HashMap();
		
		data.put("name","Jacab");
		data.put("location","Duabi");
		data.put("phone","123456789");
		
		String courseArr[] = {"Java","Selenuim"};
		
		data.put("course",courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
			//http://localhost:3000/students
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Jacab"))
			.body("location",equalTo("Duabi"))
			.body("phone",equalTo("123456789"))
			.body("course[0]",equalTo("Java"))
			.body("course[1]",equalTo("Selenuim"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
			
			}
			
			
		//Deleting created record
		
		@Test(priority=2)
		void testDelete()
		{
		
			given()
			
			.when()
				.delete("http://localhost:3000/students/"+id)
				
			.then()
				.statusCode(200);
			
	
}

}
