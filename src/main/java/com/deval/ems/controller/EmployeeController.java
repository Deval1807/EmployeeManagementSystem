package com.deval.ems.controller;

import com.deval.ems.model.Employee;
import com.deval.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController         // Define class as a rest controller
@RequestMapping("/api/employees")
public class EmployeeController {

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // use the EmployeeService object to use all the methods
    @Autowired
    private EmployeeService employeeService;

    // Get all the employees
    @GetMapping()
    public List<Employee> getEmployees() {
//        System.out.println("Get all employees");
//        return null;
        logger.info("Fetching all the employees");
        return employeeService.getEmployees();
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) throws Exception {
//        System.out.println("Get an employee by ID: "+ id);
//        return null;
        logger.info("Fetching employee with id: {}",id);
        return employeeService.getEmployeeById(id);
    }

    // Add a new employee
    @PostMapping()
    public Employee addEmployee(@Valid @RequestBody Employee newEmployee) {
//        System.out.println("New employee added");
//        System.out.println(newEmployee.toString());
//        return null;
        logger.info("Adding a new employee");
        return employeeService.addEmployee(newEmployee);
    }

    // Edit employee detail
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @Valid @RequestBody Employee updatedEmployee) throws Exception {
//        System.out.println("Update employee with ID: "+ id);
//        System.out.println(updatedEmployee.toString());
//        return null;
        logger.info("Updating an existing employee with id: {}",id);
        return employeeService.editEmployee(id, updatedEmployee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) throws Exception {
        employeeService.deleteEmployee(id);
        logger.info("Deleting employee with id: {}",id);
        return "Employee deleted with ID: "+id;
    }

}
