package com.example.jdbc.service.impl;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.domain.Customer;
import com.example.jdbc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JdbcCustomerService implements CustomerService {

    @Autowired
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
