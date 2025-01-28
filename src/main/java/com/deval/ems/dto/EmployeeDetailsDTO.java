package com.deval.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO to handle the RequestBody for updating employee
 */
@Data
public class EmployeeDetailsDTO {

    @Schema(description = "name of the employee", example = "John Doe")
    private String name;

    @Schema(description = "department id of the employee", example = "1")
    private Integer departmentId;

    @Schema(description = "phone number of the employee", example = "1234512345")
    private String phone;

    @Schema(description = "joining date of the employee", example = "2021-01-21")
    private LocalDate joiningDate;

    @Schema(description = "salary of the employee", example = "35500.50")
    private Double salary;
}
