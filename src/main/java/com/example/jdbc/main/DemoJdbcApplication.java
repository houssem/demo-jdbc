package com.example.jdbc.main;

import com.example.config.ApplicationConfig;
import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.domain.Customer;
import com.example.jdbc.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Import({ApplicationConfig.class})
public class DemoJdbcApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoJdbcApplication.class);
		CustomerRepository customerRepository = context.getBean("customerRepository", CustomerRepository.class);
		int count = customerRepository.getCustomerCount();
		System.out.println("Customer count = "+count);

		Map<String, Object> person = customerRepository.getPersonInfo(1);
		System.out.println("Person : "+person);

		List<Map<String, Object>> persons = customerRepository.getAllPersonInfo();
		System.out.println("Persons : "+persons);

		Customer customer = customerRepository.getCustomer(1);
		System.out.println("Customer : "+customer);

		List<Customer> customers = customerRepository.getAllCustomers();
		System.out.println("All customers : "+customers);

		customers = customerRepository.getAllCustomersV2();
		System.out.println("V2 for All customers : "+customers);

		List<Customer> results = new ArrayList<>();
		customerRepository.generateReports(results);
		System.out.println("List of all customers to report: "+results);

		results = new ArrayList<>();
		customerRepository.generateReports_V2(results);
		System.out.println("V2 List of all customers to report: "+results);

		List<Customer> customerList = customerRepository.getAllCustomersWithResultSetExtractor();
		System.out.println("List of all customers (ResultSetExtractor) : "+customerList);

		customerList = customerRepository.getAllCustomersWithResultSetExtractorJava8Lambda();
		System.out.println("List of all customers (ResultSetExtractor + java 8 + lambda) : "+customerList);


		// Test transaction
		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		customerService.testTransaction();

	}

}
