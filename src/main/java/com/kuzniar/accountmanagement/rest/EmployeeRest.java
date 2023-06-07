package com.kuzniar.accountmanagement.rest;

import com.kuzniar.accountmanagement.datamodel.Employee;
import com.kuzniar.accountmanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeRest {

    private EmployeeService employeeService;

    public EmployeeRest(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST,    consumes = "application/json", produces = "application/json")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
}
