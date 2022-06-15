package com.example.customer.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import com.example.customer.model.Customer;

import java.time.ZoneId;

public class CustomerBaseUrl {
    public static Customer customer;
    /*static {
        Customer customer = new Customer();
        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setEmailAddress(faker.internet().emailAddress());
        customer.setPassword(faker.internet().password());
        customer.setBirthDate(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }*/
    protected RequestSpecification spec;

    @BeforeEach
    public void setup(){
        spec = new RequestSpecBuilder().setBaseUri("http://localhost:8080/api/customer/").build();
    }
}
