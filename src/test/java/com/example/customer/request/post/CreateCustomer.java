package com.example.customer.request.post;

import com.example.customer.CustomerInitializer;
import com.example.customer.base_urls.CustomerBaseUrl;
import com.example.customer.model.Customer;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class CreateCustomer extends CustomerBaseUrl {

//    @Test
    public void testCreateCustomer() {

        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
//        Customer customer = new Customer();
//        customer.setFirstName(faker.name().firstName());
//        customer.setLastName(faker.name().lastName());
//        customer.setEmailAddress(email);
//        customer.setPassword(faker.internet().password());
//        customer.setBirthDate(faker.date().birthday().toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate());
        CustomerInitializer customerInitializer = CustomerInitializer.getInstance();
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(customerInitializer.getCustomer())
                .when()
                .post("/add");

        response.then().statusCode(200);
        response.prettyPrint();
    }
}
