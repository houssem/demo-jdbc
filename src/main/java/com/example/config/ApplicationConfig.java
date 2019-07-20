package com.example.config;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.dao.impl.JdbcCustomerRepository;
import com.example.jdbc.service.CustomerService;
import com.example.jdbc.service.impl.JdbcCustomerService;
import com.example.transaction.service.TransactionIsolationService;
import com.example.transaction.service.impl.TransactionIsolationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
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
