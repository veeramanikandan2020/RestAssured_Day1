package day1.restassured;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest {

    @Test
    public void createUser() {

        HashMap<String, String> data = new HashMap<String, String>();

        data.put("name", "Pavan");
        data.put("Job", "Trainer");

        for(Map.Entry<String,String> entry : data.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        int id = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }
}
