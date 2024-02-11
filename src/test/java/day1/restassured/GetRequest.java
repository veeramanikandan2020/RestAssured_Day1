package day1.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetRequest {
    @Test
    public void getUser() {
    // list user 2 details alone
        when().get("https://reqres.in/api/users/2")
                .then().statusCode(200)
                .log().all();
    }
}
