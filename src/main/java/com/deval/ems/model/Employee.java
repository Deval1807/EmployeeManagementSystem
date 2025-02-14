package com.deval.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor // by default @Data won't generate constructor with args
@NoArgsConstructor // Needed for modelmapper
public class Employee {
    private Integer emp_id;
    private String name;
    private Integer department_id;
    private String phone;
    private LocalDate joining_date;
    private Double salary;
}