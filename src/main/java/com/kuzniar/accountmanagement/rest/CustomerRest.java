package com.kuzniar.accountmanagement.rest;

import com.kuzniar.accountmanagement.datamodel.Customer;
import com.kuzniar.accountmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")

public class CustomerRest {

    private CustomerService customerService;

    @Autowired
    public CustomerRest(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.getCustomer(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
