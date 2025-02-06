package com.deval.ems.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * This method is to configure Database 1
     * This method reads the DB configuration with a given prefix (spring.datasource.db1)
     * and creates a DataSource object and returns it
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * This method is to configure Database 2
     * This method reads the DB configuration with a given prefix (spring.datasource.db2)
     * and creates a DataSource object and returns it
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * This method uses the "db1DataSource" bean (using @Qualifier) and creates a JdbcTemplate
     * This JdbcTemplate can be used to query on Database 1
     */
    @Bean(name = "jdbcTemplate1")
    @Primary
    public JdbcTemplate jdbcTemplate1(@Qualifier("db1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * This method uses the "db2DataSource" bean (using @Qualifier) and creates a JdbcTemplate
     * This JdbcTemplate can be used to query on Database 2
     */
    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
