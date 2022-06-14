package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/get/{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email)  {
        return customerService.getByEmail(email);
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer)  {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer)  {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteCustomer(@PathVariable("email") String email) {
        customerService.deleteCustomer(email);
    }
}
