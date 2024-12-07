package com.deval.ems.model;

import java.util.Date;
import java.util.List;

public class Employee {
    private int empId;
    private String name;
    private String departmentName;
    private int phone;
    private Date date;
    private Double salary;
    private List<String> projectList;

    // Constructor
    public Employee(int empId, String name, String departmentName, int phone, Date date, Double salary, List<String> projectList) {
        this.empId = empId;
        this.name = name;
        this.departmentName = departmentName;
        this.phone = phone;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}