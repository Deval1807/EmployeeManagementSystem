package com.deval.ems.config;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.model.Employee;
import com.deval.ems.request.NewEmployeeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    // Make the model mapper available to inject anywhere
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Explicitly mapping of department_id -> departmentId
        // Though ModelMapper does convert underscore to camelcase
        // since it's an 'id' it treats it differently -> hence need to do explicitly
        modelMapper.typeMap(Employee.class, EmployeeDTO.class).addMappings(mapper -> {
            mapper.map(Employee::getDepartment_id,
                    EmployeeDTO::setDepartmentId);
        });

        // to map from UpdateEmployeeDTO to Employee
        modelMapper.typeMap(NewEmployeeRequest.class, Employee.class).addMappings(mapper -> {
            mapper.map(NewEmployeeRequest::getDepartmentId,
                    Employee::setDepartment_id);
            mapper.map(NewEmployeeRequest::getJoiningDate,
                    Employee::setJoining_date);
        });

        // Skip null values during mapping
        // When converting from UpdateEmployeeDTO to Employee
        // There can many fields with null value
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }
}
