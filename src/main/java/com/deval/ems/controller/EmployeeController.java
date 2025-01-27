package com.deval.ems.controller;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.dto.UpdateEmployeeDTO;
import com.deval.ems.model.Employee;
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
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class))
                            )
                    }
            )
    })
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
                                    schema = @Schema(implementation = EmployeeDTO.class)
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
        logger.info("Fetching employee with id: {}",id);
        return employeeService.getEmployeeById(id);
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
                    content = @Content()
            )
    })
    @PostMapping()
    public String addEmployee(@Valid @RequestBody UpdateEmployeeDTO newEmployee) {
        logger.info("Adding a new employee");
        return employeeService.addEmployee(newEmployee);
    }

    /**
     * Updates an employee by id
     * @param id The id of the employee to be updated
     * @param updateEmployeeDTO UpdateEmployeeDTO object of employee fields to be updated
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
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content()
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
    public EmployeeDTO updateEmployee(@PathVariable int id, @RequestBody UpdateEmployeeDTO updateEmployeeDTO){
        logger.info("Updating an existing employee with id: {}",id);
        return employeeService.editEmployee(id, updateEmployeeDTO);
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
        employeeService.deleteEmployee(id);
        logger.info("Deleting employee with id: {}",id);
        return "Employee deleted with ID: "+id;
    }

}
