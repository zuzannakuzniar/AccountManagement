package com.kuzniar.accountmanagement.service;

import com.kuzniar.accountmanagement.datamodel.Customer;
import com.kuzniar.accountmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerService() {
    }

    public Customer createCustomer(Customer customer) {
       return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public Customer getCustomerByLogin(String login) {
       return customerRepository.findCustomerByLogin(login);
    }

    public void updateCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if(existingCustomer.isPresent()){
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setAccount(customer.getAccount());
            updatedCustomer.setLogin(customer.getLogin());
            updatedCustomer.setPassword(customer.getPassword());
            updatedCustomer.setUserType(customer.getUserType());
            customerRepository.save(updatedCustomer);
        } else {
            customerRepository.save(customer);
        }
    }

    public void deleteCustomer(Customer customer) {
        if(customerRepository.existsById(customer.getId())){
            customerRepository.delete(customer);
        }
    }

}
