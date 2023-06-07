package com.kuzniar.accountmanagement.repository;

import com.kuzniar.accountmanagement.datamodel.Account;
import com.kuzniar.accountmanagement.datamodel.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
