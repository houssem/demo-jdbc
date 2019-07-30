package com.example.config;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.dao.impl.JdbcCustomerRepository;
import com.example.jdbc.service.CustomerService;
import com.example.jdbc.service.impl.JdbcCustomerService;
import com.example.transaction.service.TransactionIsolationService;
import com.example.transaction.service.impl.TransactionIsolationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// @Configuration
// @SpringBootApplication
// @SpringBootConfiguration
// @EnableAutoConfiguration
// @ComponentScan
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean
    public CustomerRepository customerRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcCustomerRepository(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Value(value = "${db.url}")
    private String url;
    @Value(value = "${db.username}")
    private String userName;
    @Value(value = "${db.password}")
    private String password;



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository) {
        return new JdbcCustomerService(customerRepository);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionIsolationService transactionIsolationService(CustomerRepository customerRepository) {
        return  new TransactionIsolationServiceImpl(customerRepository);
    }

}
