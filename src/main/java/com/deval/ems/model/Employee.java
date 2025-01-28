package com.deval.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;
    private String name;
    private int department_id;
    private String phone;
    private LocalDate joining_date;
    private Double salary;

    // Constructor


    public Employee(int emp_id, String name, int department_id, String phone, LocalDate joining_date, Double salary) {
        this.emp_id = emp_id;
        this.name = name;
        this.department_id = department_id;
        this.phone = phone;
        this.joining_date = joining_date;
        this.salary = salary;
    }

    public Employee() {
    }

    // Getters and Setters


    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(LocalDate joining_date) {
        this.joining_date = joining_date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // toString method to display/print the object
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", name='" + name + '\'' +
                ", department_id='" + department_id + '\'' +
                ", phone=" + phone +
                ", joining_date=" + joining_date +
                ", salary=" + salary +
                '}';
    }

}