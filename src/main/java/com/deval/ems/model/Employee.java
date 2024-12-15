package com.deval.ems.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class Employee {
    private int empId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters")
    private String name;

    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 40, message = "Department name must be between 2 to 40 characters")
    private String departmentName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be a valid 10-digit number")
    private String phone;

    @NotNull(message = "Salary is required")
    @PastOrPresent(message = "Joining Date must be in past or present")
    private LocalDate joiningDate;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;

    @NotNull(message = "Project list is required")
    private List<String> projectList;

    // Constructor
    public Employee(int empId, String name, String departmentName, String phone, LocalDate joiningDate, Double salary, List<String> projectList) {
        this.empId = empId;
        this.name = name;
        this.departmentName = departmentName;
        this.phone = phone;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.projectList = projectList;
    }

    // Getters and Setters

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    // toString method to display/print the object
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", phone=" + phone +
                ", joiningDate=" + joiningDate +
                ", salary=" + salary +
                ", projectList=" + projectList +
                '}';
    }

}