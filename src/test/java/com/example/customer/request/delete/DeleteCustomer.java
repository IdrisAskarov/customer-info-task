package com.example.customer.request.delete;


import com.example.customer.CustomerInitializer;
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
