package com.example.jdbc.config;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.dao.impl.JdbcCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public CustomerRepository customerRepository() {
        return new JdbcCustomerRepository(getJdbcTemplate());
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSrouce());
    }

    @Bean
    public DataSource getDataSrouce() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://localhost/jdbc");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

}
