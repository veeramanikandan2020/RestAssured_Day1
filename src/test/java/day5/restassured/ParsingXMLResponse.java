package day5.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

	//@Test
	void testxmlResponse() {
		given()

				.when()

				.get("http://restapi.adequateshop.com/api/Traveler?page=1")

				.then()

				.statusCode(200).header("Content-Type", "application/xml; charset=utf-8")
				// .body("TravelerinformationResponse.page", equalTo(1))
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));

	}

	@Test
	void testxmlResponsebody() {

		Response res = given()

				.when()

				.get("http://restapi.adequateshop.com/api/Traveler?page=1");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");

		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();

		System.out.println("Page No is :"+pageNo);
		Assert.assertEquals(pageNo, "1");

		String travellerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name")
				.toString();

		Assert.assertEquals(travellerName, "Developer");
		
		System.out.println("TravellerName of 0 object is :"+travellerName);

		String travellerName6 = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[6].name")
				.toString();
		Assert.assertEquals(travellerName6, "asdasd");
		
		System.out.println("TravellerName of 6 object is :"+travellerName6);
	}
	
	@Test
	void testxmlobject() {
		
		Response res = given()          
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xml = new XmlPath(res.asString());
		
		List<Object> list_Travellers = xml.getList("TravelerinformationResponse.travelers.Travelerinformation");
	

		System.out.println("Total list of Travellers : "+list_Travellers.size());
		
		
		List<Object> list_Names = xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		for (Object object : list_Names) {
			
			System.out.println(object);
		}
		
	}
	
}
