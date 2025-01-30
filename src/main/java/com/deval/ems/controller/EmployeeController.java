package com.deval.ems.controller;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.request.NewEmployeeRequest;
import com.deval.ems.request.UpdateEmployeeRequest;
import com.deval.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
    @Operation(
            summary = "Get all the employees",
            description = "Get a list of all the employees in EmployeeDTO format",
            tags = "Employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of employee list",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            type = "object",
                                            example = "{ \"name\": \"John Doe\", \"departmentId\": 1, \"joiningDate\": \"2021-01-21\" }"
                                    )
                            )
                    )
            )

    })
    @GetMapping()
    public List<EmployeeDTO> getEmployees() {
        logger.info("IN: Request to fetch all employees");
        List<EmployeeDTO> employees = employeeService.getEmployees();
        logger.info("OUT: Successfully fetched all the employees");
        return employees;
    }

    /**
     * Retrieves employee details by employee id
     * @param id The id of employee to be retrieved
     * @return EmployeeDTO object representing the employee with given id
     */
    @Operation(
            summary = "Get an employee",
            description = "Get an employee in EmployeeDTO format by ID",
            tags = "Employee"
    )
    @Parameter(name = "id", description = "ID of the employee to be found", required = true)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of the employee",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "object",
                                            example = "{ \"name\": \"John Doe\", \"departmentId\": 1, \"joiningDate\": \"2021-01-21\" }"
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"error\": \"Not Found\", \"message\": \"Employee with ID 1 not found.\" }"
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        logger.info("IN: Request to fetch an employee with ID: {}",id);
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        logger.info("OUT: Successfully fetched an employee with ID: {}",id);
        return employee;
    }

    /**
     * Adds a new employee
     * @param newEmployee Employee object representing new employee
     * @return String message indicating result (success or failure)
     */
    @Operation(
            summary = "Adds a new employee",
            description = "Saves a new employee to the Database with fields provided",
            tags = "Employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee added successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = String.class,
                                    example = "New employee added successfully!"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/jons",
                                    schema = @Schema(
                                            example = "{\"error\": \"Database integrity error: Phone number already exists. Please enter a new phone number\"}"
                                    )
                            )
                    }
            )
    })
    @PostMapping()
    public String addEmployee(@Valid @RequestBody NewEmployeeRequest newEmployee) {
        logger.info("IN: Request to add a new employee with body body: {}",newEmployee);
        String res = employeeService.addEmployee(newEmployee);
        logger.info("OUT: Successfully added a new employee with body: {}",newEmployee);
        return res;
    }

    /**
     * Updates an employee by id
     * @param id The id of the employee to be updated
     * @param updateEmployee Employee object of employee fields to be updated
     * @return EmployeeDTO object representing updated employee
     */
    @Operation(
            summary = "Updates an existing employee",
            description = "Update only the provided fields of an existing employee by ID",
            tags = "Employee"
    )
    @Parameter(name = "id", description = "ID of the employee to be updated", required = true)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated the employee",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "object",
                                            example = "{ \"name\": \"John Doe\", \"departmentId\": 1, \"joiningDate\": \"2021-01-21\" }"
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/jons",
                                    schema = @Schema(
                                            example = "{\"error\": \"Database integrity error: Phone number already exists. Please enter a new phone number\"}"
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"error\": \"Not Found\", \"message\": \"Employee with ID 1 not found.\" }"
                            )
                    )
            )
    })
    @PatchMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable int id, @Valid @RequestBody UpdateEmployeeRequest updateEmployee) {
        logger.info("IN: Request to update the employee with id: {}",id);
        EmployeeDTO updatedEmployee = employeeService.editEmployee(id, updateEmployee);
        logger.info("OUT: Successfully updated the employee with id: {}",id);
        return updatedEmployee;
    }

    /**
     * Deletes an employee by id
     * @param id The id of the employee to be deleted
     * @return A message of success or throws error
     */
    @Operation(
            summary = "Delete an employee",
            description = "Deletes the employee with the specified ID from the database."
    )
    @Parameter(name = "id", description = "ID of the employee to be deleted", required = true)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee deleted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = String.class,
                                    example = "Employee deleted with ID: 17"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"error\": \"Not Found\", \"message\": \"Employee with ID 1 not found.\" }"
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        logger.info("IN: Request to delete an employee with id: {}",id);
        employeeService.deleteEmployee(id);
        logger.info("OUT: Successfully deleted employee with id: {}",id);
        return "Employee deleted with ID: "+id;
    }

}
