package com.example.customer.request;


import com.example.customer.CustomerInitializer;
import com.example.customer.base_urls.CustomerBaseUrl;
import com.example.customer.model.Customer;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTests extends CustomerBaseUrl {


    @Test
    @Order(1)
    public void testCreateCustomer() {
        Customer customer = CustomerInitializer.getInstance().getCustomer();
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(customer)
                .when()
                .post("/add");

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    @Order(2)
    public void testCreateExistingCustomer() {
        Customer customer = CustomerInitializer.getInstance().getCustomer();
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(customer)
                .when()
                .post("/add");

        response.then().statusCode(500);
        response.prettyPrint();
    }

    @Test
    @Order(3)
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

    @Test
    @Order(4)
    public void updateCustomer(){
        Customer customer = CustomerInitializer.getInstance().getCustomer();

        Faker faker = new Faker();
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

    @Test
    @Order(5)
    public void deleteCustomer(){
        Customer customer = CustomerInitializer.getInstance().getCustomer();

        spec.pathParam("email",customer.getEmailAddress());

        Response response = given()
                .spec(spec)
                .when()
                .delete("/delete/{email}");

        response.then().statusCode(200);
        response.prettyPrint();
    }
}
