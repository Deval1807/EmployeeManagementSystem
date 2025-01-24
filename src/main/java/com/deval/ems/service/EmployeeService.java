package com.deval.ems.service;

import com.deval.ems.dao.EmployeeDAO;
import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.model.Employee;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    // Define a map for storing employee details
//    private Map<Integer, Employee> employeeDetails = new HashMap<>();
//    int currentId = 1;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private ModelMapper modelMapper;

    // Get all the employees
    public List<EmployeeDTO> getEmployees() {
        // return all the employees in an array form
//        return new ArrayList<>(employeeDetails.values());
        logger.info("Querying DB to find all employees");
        // Fetch the list of employees from DAO
        List<Employee> employees = employeeDAO.findAll();

        // Map the list of Employee entities to EmployeeDTOs
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    // Get an employee by ID
    public EmployeeDTO getEmployeeById(int id){

        // check if the ID exists
//        if(!employeeDetails.containsKey(id)) {
//            logger.error("Employee not found with ID: {} while fetching employee",id);
//            throw new Exception("Employee not found with ID: "+id);
//        }
//        return employeeDetails.get(id);

        logger.info("Fetching employee with ID: {} from DB", id);
//        return employeeDAO.findById(id).orElseThrow(() -> {
//            logger.error("Employee not found with ID: {}", id);
//            return new Exception("Employee not found with ID: " + id);
//        });

        Employee employee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        return modelMapper.map(employee, EmployeeDTO.class);

    }

    // Add a new employee
    public String addEmployee(Employee newEmployee) {
        // set the id to current id and add it to our hash map
//        newEmployee.setEmpId(currentId);
//        employeeDetails.put(currentId, newEmployee);
//
//        // increase the counter for hash map
//        currentId++;
//
//        return newEmployee;


        try {
            logger.info("Adding a new employee to the database");
            return employeeDAO.save(newEmployee); // This will throw an exception if the phone is duplicate
        } catch (DataIntegrityViolationException e) {
            logger.error("Error occurred while adding a new employee to database");
            throw e; // Let the global exception handler manage this
        }
    }

    // Edit an employee by id
    public EmployeeDTO editEmployee(int id, Map<String, Object> updates)  {
        // check if the ID exists
//        if(!employeeDetails.containsKey(id)) {
//            logger.error("Employee not found with ID: {} while editing employee",id);
//            throw new Exception("Employee not found with ID: "+id);
//        }
//
//        // Set the empId and change the entry of map corresponding to the id
//        updatedEmployee.setEmpId(id);
//        employeeDetails.put(id, updatedEmployee);
//
//        return updatedEmployee;

        logger.info("Editing employee with ID: {} in the DB", id);

        Employee existingEmployee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        // Update only the required fields
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingEmployee.setName((String) value);
                    break;
                case "department_id":
                    existingEmployee.setDepartment_id((Integer) value);
                    break;
                case "phone":
                    existingEmployee.setPhone((String) value);
                    break;
                case "joining_date":
                    existingEmployee.setJoining_date(LocalDate.parse((String) value));
                    break;
                case "salary":
                    existingEmployee.setSalary(Double.parseDouble(value.toString()));
                    break;
                default:
                    logger.warn("Unknown field: {}. Skipping update for it.", key);
            }
        });

        try {
            logger.info("Updating employee to the database");
            Employee employee = employeeDAO.update(existingEmployee);
            return modelMapper.map(employee, EmployeeDTO.class);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error occurred while updating the employee to database");
            throw e; // Let the global exception handler manage this
        }

    }

    // Delete an employee by id
    public void deleteEmployee(int id) throws Exception {
        // check if the ID exists
//        if(!employeeDetails.containsKey(id)) {
//            logger.error("Employee not found with ID: {} while deleting employee",id);
//            throw new Exception("Employee not found with ID: "+id);
//        }
//
//        employeeDetails.remove(id);

        logger.info("Deleting employee with ID: {} in the DB", id);

        Employee existingEmployee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        employeeDAO.delete(existingEmployee);
    }

}
