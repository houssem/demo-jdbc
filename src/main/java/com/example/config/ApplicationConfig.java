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
@EnableAutoConfiguration
@ComponentScan(value = "com.example")
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionIsolationService transactionIsolationService(CustomerRepository customerRepository) {
        return  new TransactionIsolationServiceImpl(customerRepository);
    }

}
