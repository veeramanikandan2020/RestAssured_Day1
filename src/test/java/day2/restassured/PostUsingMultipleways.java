package day2.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostUsingMultipleways {

	//@Test
	void testPostHashMap() {

		HashMap data = new HashMap();

		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "12345678");

		String courseArr[] = { "C", "C++" };

		data.put("course", courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students")
				// http://localhost:3000/students

				.then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
				.body("phone", equalTo("12345678")).body("course[0]", equalTo("C")).body("course[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// Deleting created record

	//@Test(priority = 2)
	void testDelete() {

		given()

				.when().delete("http://localhost:3000/students/4")

				.then().statusCode(200);

	}
	
	//@Test(priority = 1)
	void postusingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Mani");
		data.put("location", "USA");
		data.put("phone", "981273845");

		String courseArr[] = { "Python", "RestAssured" };

		data.put("course", courseArr);

		given().contentType("application/json").body(data.toString())
		
		.when().post("http://localhost:3000/students")
				// http://localhost:3000/students

		.then()
			.statusCode(201).body("name", equalTo("Mani"))
			.body("location", equalTo("USA"))
				.body("phone", equalTo("981273845"))
				.body("course[0]", equalTo("Python"))
				.body("course[1]", equalTo("RestAssured"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();

	}
	
	//@Test (priority = 4)
	void postUsingPOJO() {
		// TODO Auto-generated method stub

		PostUsingPOJO data = new PostUsingPOJO();
		
		data.setName("Akash");
		data.setLocation("Mexico");
		data.setPhone("938848433");
		
		String course[] = {"Pascal","Fortan"};
		data.setCourses(course);
		
		given()
		
			.contentType("application/json")
			.body(data)
		
		.when().post("http://localhost:3000/students")
				// http://localhost:3000/students

		.then()
			.statusCode(201).body("name", equalTo("Akash"))
			.body("location", equalTo("Mexico"))
				.body("phone", equalTo("938848433"))
				.body("courses[0]", equalTo("Pascal"))
				.body("courses[1]", equalTo("Fortan"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
	}
	
	
	@Test (priority = 5)
	void postUsingexternalJson() throws FileNotFoundException {
		// TODO Auto-generated method stub

		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		given()
		
			.contentType("application/json")
			.body(data.toString())
		
		.when().post("http://localhost:3000/students")
				// http://localhost:3000/students

		.then()
			.statusCode(201)
			.body("name", equalTo("Duraibalaji"))
			.body("location", equalTo("Saudhi"))
				.body("phone", equalTo("987654544"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("Java"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
	}

}
