package com.example.customer.request.get;


import com.example.customer.base_urls.CustomerBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class GetCustomer extends CustomerBaseUrl {

    @Test
    public void getCustomerByEmail() {
        spec.pathParam("email", "ernie.hudson@hotmail.com");

        Response response = given()
                .spec(spec)
                .accept(ContentType.JSON)
                .when()
                .get("/get/{email}");
        String prettyPrint = response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("emailAddress",equalTo("ernie.hudson@hotmail.com"));
    }
}
