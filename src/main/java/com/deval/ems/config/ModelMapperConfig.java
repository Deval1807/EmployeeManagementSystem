package com.deval.ems.config;

import com.deval.ems.dto.EmployeeDTO;
import com.deval.ems.model.Employee;
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

        return modelMapper;
    }
}
