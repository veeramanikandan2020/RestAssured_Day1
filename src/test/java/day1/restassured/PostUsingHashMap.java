package day1.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostUsingHashMap {

@Test		
void testPostHashMap(){
	
	HashMap data = new HashMap();
		
		data.put("name","Scott");
		data.put("location","France");
		data.put("phone","phone");
		
		String courseArr[] = {"C","C++"};
		
		data.put("course",courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone",equalTo("phone"))
			.body("course[0]",equalTo("C"))
			.body("course[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
			
			}
			
			
		//Deleting created record
		
		@Test(priority=2)
		void testDelete()
		{
		
			given()
			
			.when()
				.delete("http://localhost:3000/students/4")
				
			.then()
				.statusCode(200);
			
	
}

}
