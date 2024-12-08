package com.deval.ems.service;

import com.deval.ems.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    // Define a map for storing employee details
    private Map<Integer, Employee> employeeDetails = new HashMap<>();
    int currentId = 1;

    // Get all the employees
    public List<Employee> getEmployees() {
        // return all the employees in an array form
        return new ArrayList<>(employeeDetails.values());
    }

    // Get an employee by ID
    public Employee getEmployeeById(int id) throws Exception {

        // check if the ID exists
        if(!employeeDetails.containsKey(id)) {
            throw new Exception("Employee not found with ID: "+id);
        }
        return employeeDetails.get(id);

    }

    // Add a new employee
    public Employee addEmployee(Employee newEmployee) {
        // set the id to current id and add it to our hash map
        newEmployee.setEmpId(currentId);
        employeeDetails.put(currentId, newEmployee);

        // increase the counter for hash map
        currentId++;

        return newEmployee;
    }

    // Edit an employee by id
    public Employee editEmployee(int id, Employee updatedEmployee) throws Exception {
        // check if the ID exists
        if(!employeeDetails.containsKey(id)) {
            throw new Exception("Employee not found with ID: "+id);
        }

        // Set the empId and change the entry of map corresponding to the id
        updatedEmployee.setEmpId(id);
        employeeDetails.put(id, updatedEmployee);

        return updatedEmployee;
    }

    // Delete an employee by id
    public void deleteEmployee(int id) throws Exception {
        // check if the ID exists
        if(!employeeDetails.containsKey(id)) {
            throw new Exception("Employee not found with ID: "+id);
        }

        employeeDetails.remove(id);
    }

}
