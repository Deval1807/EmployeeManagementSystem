package com.deval.ems.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor // by default @Data won't generate constructor with args
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
}