package payments.services;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIClient {

    public RequestSpecification createBaseSpec(){
        RequestSpecification request = given().baseUri("http://localhost:8089/")
                .header("Authorization", "Bearer ")
                .header("Content-Type", "application/json");

        return request;
    }
}
