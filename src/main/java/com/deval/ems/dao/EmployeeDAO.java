package com.deval.ems.dao;

import com.deval.ems.model.Employee;
import com.deval.ems.request.UpdateEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This method maps the ResultSet(result from SQL operation) to Employee object
     */
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

    private Employee mapEmployeeRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getInt("department_id"),
                rs.getString("phone"),
                rs.getDate("joining_date").toLocalDate(),
                rs.getDouble("salary")
        );
    }

    /**
     * This method queries the DB and find all the employees
     * @return List of Employee objects
     */
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";

//        return jdbcTemplate.query(sql, new EmployeeRowMapper());

//        return jdbcTemplate.query(sql, (rs, rowNum) ->
//                new Employee(
//                        rs.getInt("emp_id"),
//                        rs.getString("name"),
//                        rs.getInt("department_id"),
//                        rs.getString("phone"),
//                        rs.getDate("joining_date").toLocalDate(),
//                        rs.getDouble("salary")
//                )
//        );

        // this::mapEmployeeRow --> method reference to pass it as a callback function
        return jdbcTemplate.query(sql, this::mapEmployeeRow);
    }

    /**
     * This method queries the DB to find an employee by id
     * @param id id of employee to be found
     * @return Optional Employee object
     */
    public Optional<Employee> findById(int id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        List<Employee> result = jdbcTemplate.query(sql, new EmployeeRowMapper(), id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    /**
     * This method queries DB to add a new employee
     * @param employee Employee object - new employee details to be added
     * @return A string message (success or failure)
     */
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


    /**
     * This method queries DB to update an employee by id
     *
     * @param updateEmployee Employee object - updated employee details
     * @returns updated Employee object
     */
    public void update(int id, Employee updateEmployee) {
        // build the query dynamically
        StringBuilder dynamicSql = new StringBuilder("UPDATE employees SET ");

        // store the parameter values
        List<Object> params = new ArrayList<>();

        if(updateEmployee.getName() != null) {
            dynamicSql.append("name = ?, ");
            params.add(updateEmployee.getName());
        }
        if (updateEmployee.getDepartment_id() != null) {
            dynamicSql.append("department_id = ?, ");
            params.add(updateEmployee.getDepartment_id());
        }
        if (updateEmployee.getPhone() != null) {
            dynamicSql.append("phone = ?, ");
            params.add(updateEmployee.getPhone());
        }
        if (updateEmployee.getJoining_date() != null) {
            dynamicSql.append("joining_date = ?, ");
            params.add(updateEmployee.getJoining_date());
        }
        if (updateEmployee.getSalary() != null) {
            dynamicSql.append("salary = ?, ");
            params.add(updateEmployee.getSalary());
        }

        // it will have extra comma and space
        // so remove last 2 characters
        dynamicSql.setLength(dynamicSql.length() - 2);

        dynamicSql.append(" WHERE emp_id = ?");

        // add the employee id to params list
        params.add(id);

        jdbcTemplate.update(dynamicSql.toString(), params.toArray());
    }

    /**
     * This method queries DB to delete an employee by id
     * @param employee Employee object
     */
    public void delete(Employee employee) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        jdbcTemplate.update(sql, employee.getEmp_id());
    }
}
