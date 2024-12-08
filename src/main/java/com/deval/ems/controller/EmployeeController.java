package com.deval.ems.controller;

import com.deval.ems.model.Employee;
import com.deval.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController         // Define class as a rest controller
@RequestMapping("/api/employees")
public class EmployeeController {

    // use the EmployeeService object to use all the methods
    @Autowired
    private EmployeeService employeeService;

    // Get all the employees
    @GetMapping()
    public List<Employee> getEmployees() {
//        System.out.println("Get all employees");
//        return null;
        return employeeService.getEmployees();
    }

    // Get an employee by ID
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) throws Exception {
//        System.out.println("Get an employee by ID: "+ id);
//        return null;
        return employeeService.getEmployeeById(id);
    }

    // Add a new employee
    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee) {
//        System.out.println("New employee added");
//        System.out.println(newEmployee.toString());
//        return null;
        return employeeService.addEmployee(newEmployee);
    }

    // Edit employee detail
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) throws Exception {
//        System.out.println("Update employee with ID: "+ id);
//        System.out.println(updatedEmployee.toString());
//        return null;
        return employeeService.editEmployee(id, updatedEmployee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) throws Exception {
        employeeService.deleteEmployee(id);
        return "Employee deleted with ID: "+id;
    }

}
