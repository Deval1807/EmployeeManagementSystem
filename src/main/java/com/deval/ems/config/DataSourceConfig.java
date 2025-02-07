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
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "mysql.ems.database")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * This method is to configure Database 2
     * This method reads the DB configuration with a given prefix (spring.datasource.db2)
     * and creates a DataSource object and returns it
     */
    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "postgres.clients.database")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * This method uses the "mysqlDataSource" bean (using @Qualifier) and creates a JdbcTemplate
     * This JdbcTemplate can be used to query on Database 1 - mysql
     */
    @Bean(name = "mysqlEmsJdbcTemplate")
    @Primary
    public JdbcTemplate mysqlEmsJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * This method uses the "postgresDataSource" bean (using @Qualifier) and creates a JdbcTemplate
     * This JdbcTemplate can be used to query on Database 2 - postgres
     */
    @Bean(name = "postgresClientsJdbcTemplate")
    public JdbcTemplate postgresClientsJdbcTemplate(@Qualifier("postgresDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
