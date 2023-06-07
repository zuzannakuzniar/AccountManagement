package com.kuzniar.accountmanagement.repository;

import com.kuzniar.accountmanagement.datamodel.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findCustomerByLogin(String login);
}
