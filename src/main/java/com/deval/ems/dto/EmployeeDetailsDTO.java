package com.deval.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * DTO to handle the RequestBody for updating employee
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "UpdateEmployeeDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }
}
