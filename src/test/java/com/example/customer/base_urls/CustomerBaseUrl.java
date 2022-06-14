package com.example.customer.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

public class CustomerBaseUrl {

    protected RequestSpecification spec;

    @BeforeEach
    public void setup(){
        spec = new RequestSpecBuilder().setBaseUri("http://localhost:8080/api/customer/").build();
    }
}
