package com.example.jdbc.service.impl;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.domain.Customer;
import com.example.jdbc.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

public class JdbcCustomerService implements CustomerService {

    private CustomerRepository customerRepository;

    public JdbcCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void testTransaction() {
        Customer customer = customerRepository.getCustomer(1);
        System.out.println("Before updating customer = "+customer);

//        customer.setAge(89);
//        customer.setId(1);
//        customerRepository.updateAge(customer);
//
//        customer = customerRepository.getCustomer(1);
//        System.out.println("After updating customer = "+customer);
//        throw new RuntimeException();
    }

}
