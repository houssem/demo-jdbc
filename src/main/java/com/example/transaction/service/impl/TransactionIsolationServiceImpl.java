package com.example.transaction.service.impl;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.domain.Customer;
import com.example.transaction.service.TransactionIsolationService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TransactionIsolationServiceImpl implements TransactionIsolationService {

    private CustomerRepository customerRepository;

    public TransactionIsolationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void USER1ReadUnComitted() throws Exception {
        System.out.println("USER1- start transaction");
        Customer customer = customerRepository.getCustomer(1);
        System.out.println("USER1- READ Customer: "+customer);
        customer.setId(1);
        customer.setAge(customer.getAge() + 2);
        customerRepository.updateAge(customer);
        System.out.println("USER1- Customer age is updated - customer :"+customerRepository.getCustomer(1));
        Thread.currentThread().sleep(6000);
        System.out.println("USER1- end transaction");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void USER2ReadUnComitted() throws Exception {
        System.out.println("USER2- start transaction");
        Thread.currentThread().sleep(3000);
        System.out.println("USER2- READ customer :"+customerRepository.getCustomer(1));
        System.out.println("USER2- end transaction");
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void USER1ReadComitted() throws Exception {

        /*
         * ReadRow
         */
        System.out.println("Read Customer Records USER1ReadComitted");
        List<Customer> customersFirstTime = customerRepository.getAllCustomers();

        /*
         * Read customer
         */
        if (!customersFirstTime.isEmpty()) {
            System.out.println("Size of customer list first time :"+customersFirstTime.size());
        }

        /*
         * Wait the other transaction read
         */
        Thread.currentThread().sleep(6000);
        /*
         * ReadRow
         */
        System.out.println("Read Customer Records USER1ReadComitted");
        List<Customer> customersSecondTime = customerRepository.getAllCustomers();
        /*
         * Read catalog
         */
        if (!customersSecondTime.isEmpty()) {
            System.out.println("Size of customer list second time :"+customersSecondTime.size());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void USER2ReadComitted() throws Exception {
        /*
         * Wait the other transaction read
         */
        System.out.println("Waiting for inserting customer record USER2ReadComitted");
        Thread.currentThread().sleep(3000);
        /*
         * Insert new item
         */
        Customer customer = new Customer();
        customer.setFirstName("Yassine");
        customer.setLastName("Poyraz");
        customer.setAge(15);
        int row = customerRepository.addNewCustomer(customer);
        System.out.println(row + "Customer Record inserted by  USER2ReadComitted");
        Thread.currentThread().sleep(6000);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void USER1Serializable() throws Exception {
        System.out.println("USER1Serializable- start transaction");
        List<Customer> customers = customerRepository.getOldCustomers(40);
        System.out.println("USER1Serializable- start first read customers having age greater then 40: "+customers);
        System.out.println("USER1Serializable- waiting for inserting new customer by Thread 2 ");
        Thread.currentThread().sleep(10000);

        System.out.println("USER1Serializable- strat second reading customers by thread 1 ");
        customers = customerRepository.getOldCustomers(40);
        System.out.println("USER1Serializable- start second read customers having age greater then 40: "+customers);

        System.out.println("USER1Serializable- end transaction");
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 5)
    public void USER2Serializable() throws Exception {
        System.out.println("USER2Serializable- start transaction");
        Thread.currentThread().sleep(2000);
        Customer customer = new Customer();
        customer.setFirstName("Mansour");
        customer.setLastName("Ben Foulen");
        customer.setAge(60);
        customerRepository.addNewCustomer(customer);
        System.out.println("USER2Serializable- new customer is created");
        System.out.println("USER2Serializable- end transaction");
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void USER1RepeatableRead() throws Exception {
        System.out.println("USER1RepeatableRead- start transaction");
        Customer customer = customerRepository.getCustomer(1);
        System.out.println("USER1RepeatableRead- start first read for customer with id 1 : "+customer);
        System.out.println("USER1RepeatableRead- waiting for updating the customer with id 1 by Thread 2 ");
        Thread.currentThread().sleep(10000);

        System.out.println("USER1RepeatableRead- strat second reading for customer with id 1 by thread 1 ");
        Customer customer1 = customerRepository.getCustomer(1);
        System.out.println("USER1RepeatableRead- start second read for customer with id 1: "+customer1);

        System.out.println("USER1RepeatableRead- end transaction");
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class, timeout = 5)
    public void USER2RepeatableRead() throws Exception {
        System.out.println("USER2RepeatableRead- start transaction");
        Thread.currentThread().sleep(2000);
        Customer customer = customerRepository.getCustomer(1);
        customer.setId(1);
        customer.setAge(customer.getAge() + 2);
        customerRepository.updateAge(customer);
        System.out.println("USER2RepeatableRead- Age for customer with id 1 was updated...");
        System.out.println("USER2RepeatableRead- end transaction");
    }

}
