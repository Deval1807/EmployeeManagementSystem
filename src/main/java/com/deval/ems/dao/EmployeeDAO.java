package com.deval.ems.dao;

import com.deval.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to Employee object
    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("name"),
                    rs.getInt("department_id"),
                    rs.getString("phone"),
                    rs.getDate("joining_date").toLocalDate(),
                    rs.getDouble("salary")
            );
        }
    }

    // Find all employees
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    // Find employee by ID
    public Optional<Employee> findById(int id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        List<Employee> result = jdbcTemplate.query(sql, new EmployeeRowMapper(), id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    // Save a new employee
    public String save(Employee employee) {
        String sql = "INSERT INTO employees (name, department_id, phone, joining_date, salary) VALUES (?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, employee.getName(), employee.getDepartment_id(), employee.getPhone(),
                employee.getJoining_date(), employee.getSalary());

        // Check if the insert was successful
        if (rowsAffected > 0) {
            return "New employee added successfully!";
        } else {
            return "Failed to add new employee!";
        }
    }


    // Update an existing employee
    public Employee update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department_id = ?, phone = ?, joining_date = ?, salary = ? WHERE emp_id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getDepartment_id(), employee.getPhone(),
                employee.getJoining_date(), employee.getSalary(), employee.getEmp_id());

        return employee;
    }

    // Delete an employee
    public void delete(Employee employee) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        jdbcTemplate.update(sql, employee.getEmp_id());
    }
}
