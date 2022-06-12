package com.example.customer.service;

import com.example.customer.exception.CustomerException;
import com.example.customer.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private static List<Customer> customerList;

    public CustomerService() {
        customerList = new ArrayList<>();
    }


    public List<Customer> getAll() {
        return customerList;
    }

    public Customer getByEmail(String email)  {
        return customerList.stream()
                .filter(customer -> customer.getEmailAddress().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new CustomerException("Customer with email: " + email + " not found"));
    }

    public Customer addCustomer(Customer customer) {
        if (!customerList.stream()
                .filter(cust -> cust.getEmailAddress().equalsIgnoreCase(customer.getEmailAddress()))
                .findFirst().stream().findAny().isEmpty()) {
            throw new CustomerException("Customer with email: " + customer.getEmailAddress() + " already exists");
        }
        customerList.add(customer);
        return customer;
    }

    public Customer saveCustomer(Customer customer)  {
        if (customerList.stream()
                .filter(cust -> cust.getEmailAddress().equalsIgnoreCase(customer.getEmailAddress()))
                .findFirst().stream().findAny().isEmpty()) {
            throw new CustomerException("Customer with email: " + customer.getEmailAddress() + " does not exist");
        }
        customerList.forEach(cust -> {
            if (customer.equals(cust)) {
                cust.setFirstName(customer.getFirstName());
                cust.setLastName(customer.getLastName());
                cust.setPassword(customer.getPassword());
                cust.setBirthDate(customer.getBirthDate());
            }
        });
        return customer;
    }

    public void deleteCustomer(String email) {
        customerList.removeIf(customer -> customer.getEmailAddress().equalsIgnoreCase(email));
    }
}
