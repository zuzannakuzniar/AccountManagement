package com.kuzniar.accountmanagement.service;

import com.kuzniar.accountmanagement.datamodel.Employee;
import com.kuzniar.accountmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setPosition(employee.getPosition());
            updatedEmployee.setSalary(employee.getSalary());
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setAccount(employee.getAccount());
            updatedEmployee.setLogin(employee.getLogin());
            updatedEmployee.setPassword(employee.getPassword());
            updatedEmployee.setUserType(employee.getUserType());
            return employeeRepository.save(updatedEmployee);
        }
        return employeeRepository.save(employee);

    }

    public Employee getEmployee(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public void deleteEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            employeeRepository.delete(employee);
        }
    }

}
