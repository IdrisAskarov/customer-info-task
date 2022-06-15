package com.example.customer.request.put;

import com.example.customer.CustomerInitializer;
import com.example.customer.base_urls.CustomerBaseUrl;
import com.example.customer.model.Customer;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static io.restassured.RestAssured.given;

public class UpdateCustomer extends CustomerBaseUrl {

    @Test
    public void updateCustomer(){
        Faker faker = new Faker();
        Customer customer = CustomerInitializer.getInstance().getCustomer();
        customer.setLastName(faker.name().lastName());
        customer.setFirstName(faker.name().firstName());

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(customer)
                .when()
                .put("/update");

        response.then().statusCode(200);
        response.prettyPrint();

    }
}
