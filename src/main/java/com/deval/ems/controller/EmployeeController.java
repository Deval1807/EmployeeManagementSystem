package com.deval.ems.controller;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.model.Employee;
import com.deval.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Employee", description = "Simple APIs for Employee Management System")
@RestController         // Define class as a rest controller
@RequestMapping("/api/employees")
public class EmployeeController {

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // use the EmployeeService object to use all the methods
    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves the details of all employees
     * @return List of EmployeeDTO object representing all employees
     */
    @GetMapping()
    public List<EmployeeDTO> getEmployees() {
        logger.info("Fetching all the employees");
        return employeeService.getEmployees();
    }

    /**
     * Retrieves employee details by employee id
     * @param id The id of employee to be retrieved
     * @return EmployeeDTO object representing the employee with given id
     */
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) throws Exception {
        logger.info("Fetching employee with id: {}",id);
        return employeeService.getEmployeeById(id);
    }

    /**
     * Adds a new employee
     * @param newEmployee Employee object representing new employee
     * @return String message indicating result (success or failure)
     */
    @PostMapping()
    public String addEmployee(@Valid @RequestBody Employee newEmployee) {
        logger.info("Adding a new employee");
        return employeeService.addEmployee(newEmployee);
    }

    /**
     * Updates an employee by id
     * @param id The id of the employee to be updated
     * @param updates A map (key-value pair) ot employee fields to be updated
     * @return EmployeeDTO object representing updated employee
     */
    @PatchMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable int id, @Valid @RequestBody Map<String, Object> updates) throws Exception {
        logger.info("Updating an existing employee with id: {}",id);
        return employeeService.editEmployee(id, updates);
    }

    /**
     * Deletes an employee by id
     * @param id The id of the employee to be deleted
     * @return A message of success or throws error
     */
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) throws Exception {
        employeeService.deleteEmployee(id);
        logger.info("Deleting employee with id: {}",id);
        return "Employee deleted with ID: "+id;
    }

}
