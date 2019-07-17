package com.example.jdbc.dao.impl;

import com.example.jdbc.dao.CustomerRepository;
import com.example.jdbc.domain.Customer;
import com.example.jdbc.rowmapper.impl.PersonMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcCustomerRepository implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getCustomerCount() {
        String sql = "select count(*) from customer";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Map getPersonInfo(int id) {
        String sql = "select * from customer where id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public List<Map<String,Object>> getAllPersonInfo() {
        String sql = "select * from customer";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Customer getCustomer(int id) {
        String sql = "select first_name, last_name from customer where id = ?";
        return jdbcTemplate.queryForObject(sql, new PersonMapper(), id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "select first_name, last_name from customer";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public List<Customer> getAllCustomersV2() {
        String sql = "select first_name, last_name from customer";
        return jdbcTemplate.query(sql, (rs,rowNum) -> new Customer (rs.getString("first_name"), rs.getString("last_name")));
    }

    @Override
    public void generateReports(List<Customer> results) {
        String sql = "select first_name, last_name from customer";

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                System.out.println(String.format("#Customer %s",rs.getString("first_name")+" "+rs.getString("last_name")));
                results.add(new Customer (rs.getString("first_name"), rs.getString("last_name")));
            }
        });
    }

    @Override
    public void generateReports_V2(List<Customer> results) {
        String sql = "select first_name, last_name from customer";

        jdbcTemplate.query(sql, (rs) -> {
                System.out.println(String.format("#Customer %s",rs.getString("first_name")+" "+rs.getString("last_name")));
                results.add(new Customer (rs.getString("first_name"), rs.getString("last_name")));
        });
    }

    @Override
    public List<Customer> getAllCustomersWithResultSetExtractor() {
        String query = "select first_name, last_name from customer";

        return jdbcTemplate.query(query, new ResultSetExtractor<List<Customer>>() {
            @Override
            public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Customer> customers = new ArrayList<>();
                while (rs.next()) {
                    customers.add(new Customer(rs.getString("first_name"), rs.getString("last_name")));
                }
                return customers;
            }
        });
    }

    @Override
    public List<Customer> getAllCustomersWithResultSetExtractorJava8Lambda() {
        String query = "select first_name, last_name from customer";

        return jdbcTemplate.query(query, (ResultSetExtractor <List<Customer>>)(rs) -> {
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(new Customer(rs.getString("first_name"), rs.getString("last_name")));
            }
            return customers;
        });
    }

    @Override
    public int updateAge(Customer customer) {
        return jdbcTemplate.update(
                "update Customer set age=? where id=?",
        customer.getAge(),
                customer.getId());
    }

}
