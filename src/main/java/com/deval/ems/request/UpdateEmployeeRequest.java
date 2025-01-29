package com.deval.ems.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEmployeeRequest {
    @Schema(description = "name of the employee", example = "John Doe")
    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters")
    private String name;

    @Schema(description = "department id of the employee", example = "1")
    private Integer departmentId;

    @Schema(description = "phone number of the employee", example = "1234512345")
    @Size(min = 10, max = 10, message = "Phone number must be a valid 10-digit number")
    private String phone;

    @Schema(description = "joining date of the employee", example = "2021-01-21")
    @PastOrPresent(message = "Joining Date must be in past or present")
    private LocalDate joiningDate;

    @Schema(description = "salary of the employee", example = "35500.50")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;
}
