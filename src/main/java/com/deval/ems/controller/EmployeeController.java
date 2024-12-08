package com.deval.ems.controller;

import com.deval.ems.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController         // Define class as a rest controller
@RequestMapping("/api/employees")
public class EmployeeController {

    // Get all the employees
    @GetMapping()
    public List<Employee> getEmployees() {
        System.out.println("Get all employees");
        return null;
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        System.out.println("Get an employee by ID: "+ id);
        return null;
    }

    // Add a new employee
    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        System.out.println("New employee added");
        System.out.println(newEmployee.toString());
        return null;
    }

    // Edit employee detail
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        System.out.println("Update employee with ID: "+ id);
        System.out.println(updatedEmployee.toString());
        return null;
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return "Employee deleted with ID: "+id;
    }

}
