package com.example.customer;

import com.example.customer.model.Customer;
import com.github.javafaker.Faker;

import java.time.ZoneId;

public class CustomerInitializer {
    public static Customer customer;
    private static CustomerInitializer customerInitializer;
    private CustomerInitializer(){
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
    public static CustomerInitializer getInstance(){
        if(customerInitializer == null){
            synchronized (CustomerInitializer.class){
                if(customerInitializer == null){
                    customerInitializer = new CustomerInitializer();
                }
            }
        }
        return customerInitializer;
    }

    public static Customer getCustomer() {
        return customer;
    }
}
