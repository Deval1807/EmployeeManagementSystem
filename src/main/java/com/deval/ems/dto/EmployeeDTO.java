package com.deval.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    // only show name, department and joining
    @Schema(description = "name of the employee", example = "John Doe")
    private String name;

    @Schema(description = "department id of the employee", example = "1")
    private int departmentId;

    @Schema(description = "joining date of the employee", example = "2021-01-21")
    private LocalDate joiningDate;
}
