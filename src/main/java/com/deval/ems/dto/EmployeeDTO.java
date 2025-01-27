package com.deval.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class EmployeeDTO {

    // only show name, department and joining

    @Schema(description = "name of the employee", example = "John Doe")
    private String name;

    @Schema(description = "department id of the employee", example = "1")
    private int departmentId;

    @Schema(description = "joining date of the employee", example = "2021-01-21")
    private LocalDate joiningDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
