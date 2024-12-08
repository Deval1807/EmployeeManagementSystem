package com.deval.ems.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Employee {
    private int empId;
    private String name;
    private String departmentName;
    private int phone;
    private LocalDate joiningDate;
    private Double salary;
    private List<String> projectList;

    // Constructor
    public Employee(int empId, String name, String departmentName, int phone, LocalDate joiningDate, Double salary, List<String> projectList) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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