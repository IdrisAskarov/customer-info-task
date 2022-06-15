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
    public static Customer customer;
    private CustomerTests(){
        Faker faker = new Faker();
        this.customer = new Customer();
        this.customer.setFirstName(faker.name().firstName());
        this.customer.setLastName(faker.name().lastName());
        this.customer.setEmailAddress(faker.internet().emailAddress());
        this.customer.setPassword(faker.internet().password());
        this.customer.setBirthDate(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

    }

    @Test
    @Order(1)
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

    @Test
    @Order(2)
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
    @Order(3)
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

    @Test
    @Order(4)
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
