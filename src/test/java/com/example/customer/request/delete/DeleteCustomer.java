package com.example.customer.request.delete;


import com.example.customer.base_urls.CustomerBaseUrl;
import com.example.customer.model.Customer;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static io.restassured.RestAssured.given;

public class DeleteCustomer extends CustomerBaseUrl {

    @Test
    public void deleteCustomer(){
        Faker faker = new Faker();

        String email = faker.internet().emailAddress();

        Customer customer = new Customer();
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setEmailAddress(email);
        customer.setPassword(faker.internet().password());
        customer.setBirthDate(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(customer)
                .when()
                .post("/add");

        response.then().statusCode(200);

        response.prettyPrint();

        spec.pathParam("email",email);

        response = given()
                .spec(spec)
                .when()
                .delete("/delete/{email}");

        response.then().statusCode(200);

        response.prettyPrint();


    }
}
