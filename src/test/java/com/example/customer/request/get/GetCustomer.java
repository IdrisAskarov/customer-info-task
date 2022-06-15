package com.example.customer.request.get;


import com.example.customer.CustomerInitializer;
import com.example.customer.base_urls.CustomerBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.customer.model.Customer;

import java.time.ZoneId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class GetCustomer extends CustomerBaseUrl {

    @Test
    public void getCustomerByEmail() {
        Customer customer = CustomerInitializer.getInstance().getCustomer();
        spec.pathParam("email", customer.getEmailAddress());


        Response response = given()
                .spec(spec)
                .accept(ContentType.JSON)
                .when()
                .get("/get/{email}");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("emailAddress",equalTo(customer.getEmailAddress()));
    }
}
