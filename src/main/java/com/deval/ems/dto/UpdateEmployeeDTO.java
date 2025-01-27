package com.deval.ems.dto;

import java.time.LocalDate;

/**
 * DTO to handle the RequestBody for updating employee
 */
public class UpdateEmployeeDTO {
    private String name;
    private Integer departmentId;
    private String phone;
    private LocalDate joiningDate;
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
