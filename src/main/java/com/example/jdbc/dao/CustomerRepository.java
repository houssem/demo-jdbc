package com.example.jdbc.dao;

import com.example.jdbc.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerRepository {

    int getCustomerCount();

    Map<String, Object> getPersonInfo(int id);

    List<Map<String,Object>> getAllPersonInfo();

    Customer getCustomer(int id);

    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersV2();

    void generateReports(List<Customer> results);

    void generateReports_V2(List<Customer> results);

    List<Customer> getAllCustomersWithResultSetExtractor();

    List<Customer> getAllCustomersWithResultSetExtractorJava8Lambda();

    public int updateAge(Customer customer);

    public int addNewCustomer(Customer customer);

    List<Customer> getOldCustomers(int i);
}
