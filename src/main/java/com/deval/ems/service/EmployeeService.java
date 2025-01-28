package com.deval.ems.service;

import com.deval.ems.dao.EmployeeDAO;
import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.dto.EmployeeDetailsDTO;
import com.deval.ems.model.Employee;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    // Logger to log all the necessary info
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetches all the employees and maps them from the
     * entity format to the DTO format before returning them.
     * @return A list of EmployeeDTO objects containing the employee details.
     */
    public List<EmployeeDTO> getEmployees() {
        logger.info("Querying DB to find all employees");
        // Fetch the list of employees from DAO
        List<Employee> employees = employeeDAO.findAll();

        // Map the list of Employee entities to EmployeeDTOs
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Fetches an employee and maps them from the
     * entity format to the DTO format before returning them.
     * @param id The id of employee to be fetched
     * @return An EmployeeDTO object containing the employee details.
     */
    public EmployeeDTO getEmployeeById(int id){

        logger.info("Fetching employee with ID: {} from DB", id);

        Employee employee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        return modelMapper.map(employee, EmployeeDTO.class);

    }

    /**
     * This method attempts to add a new employee and handles any data integrity issues,
     * such as duplicate phone numbers
     * @param newEmployeeDTO UpdateEmployeeDTO object
     * @return A message indicating the result of the operation (success or failure).
     */
    public String addEmployee(EmployeeDetailsDTO newEmployeeDTO) {

        if(newEmployeeDTO.getName() == null) {
            throw new DataIntegrityViolationException("The 'name' field is required");
        }
        if(newEmployeeDTO.getName().length()<2) {
            throw new DataIntegrityViolationException("Name has to be at least 2 characters long");
        }
        if(newEmployeeDTO.getDepartmentId() == null) {
            throw new DataIntegrityViolationException("The 'departmentId' field is required");
        }
        if(newEmployeeDTO.getPhone() == null) {
            throw new DataIntegrityViolationException("The 'phone' field is required");
        }
        if(newEmployeeDTO.getPhone().length()!=10) {
            throw new DataIntegrityViolationException("Phone should have 10 digits");
        }
        if(newEmployeeDTO.getJoiningDate() == null) {
            throw new DataIntegrityViolationException("The 'joiningDate' field is required");
        }
        if (newEmployeeDTO.getJoiningDate().isAfter(LocalDate.now())) {
            throw new DataIntegrityViolationException("The 'joiningDate' must be in the past or present");
        }
        if(newEmployeeDTO.getSalary() == null) {
            throw new DataIntegrityViolationException("The 'salary' field is required");
        }
        if (newEmployeeDTO.getSalary()<0) {
            throw new DataIntegrityViolationException("Salary should be positive");
        }

        Employee newEmployee = modelMapper.map(newEmployeeDTO, Employee.class);

        try {
            logger.info("Adding a new employee to the database");
            return employeeDAO.save(newEmployee); // This will throw an exception if the phone is duplicate
        } catch (DataIntegrityViolationException e) {
            logger.error("Error occurred while adding a new employee to database");
            throw e;
        }
    }

    /**
     * Edits the employee details of given id.
     * Only edits the specified fields, and it's not necessary to provide all the fields.
     * @param id id of the employee to be updated
     * @param employeeDetailsDTO UpdateEmployeeDTO object of employee fields to be updated
     * @return EmployeeDTO object of the updated employee
     */
    public EmployeeDTO editEmployee(int id, EmployeeDetailsDTO employeeDetailsDTO){

        if(employeeDetailsDTO.getName()!=null && employeeDetailsDTO.getName().length()<2) {
            throw new DataIntegrityViolationException("Name has to be at least 2 characters long");
        }
        if(employeeDetailsDTO.getPhone()!=null && employeeDetailsDTO.getPhone().length()!=10) {
            throw new DataIntegrityViolationException("Phone should have 10 digits");
        }
        if (employeeDetailsDTO.getJoiningDate()!=null && employeeDetailsDTO.getJoiningDate().isAfter(LocalDate.now())) {
            throw new DataIntegrityViolationException("The 'joiningDate' must be in the past or present");
        }
        if (employeeDetailsDTO.getSalary()!=null && employeeDetailsDTO.getSalary()<0) {
            throw new DataIntegrityViolationException("Salary should be positive");
        }

        logger.info("Editing employee with ID: {} in the DB", id);

        Employee existingEmployee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        logger.info("Updating employee to the database");

        // Update employee and get the result as an Optional
        Optional<Employee> employeeOptional = employeeDAO.update(existingEmployee, employeeDetailsDTO);

        // Check if the employee is present in the Optional
        if (employeeOptional.isEmpty()) {
            // If empty, throw an exception
            logger.error("Error occurred while updating the employee to database: Employee not found or update failed");
            throw new DataIntegrityViolationException("Employee update failed or employee not found.");
        } else {
            // If the employee is present, map it to EmployeeDTO and return
            Employee updatedEmployee = employeeOptional.get();
            return modelMapper.map(updatedEmployee, EmployeeDTO.class);
        }

    }

    /**
     * This method deletes an employee
     * @param id Thd id of the employee to be deleted
     */
    public void deleteEmployee(int id) {

        logger.info("Deleting employee with ID: {} in the DB", id);

        Employee existingEmployee = employeeDAO.findById(id).orElseThrow(() -> {
            logger.error("Employee not found with ID: {}", id);
            return new NoSuchElementException("Employee with ID " + id + " not found.");
        });

        employeeDAO.delete(existingEmployee);
    }

}
