package com.bank.customer_service.service;

import com.bank.customer_service.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer register(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer updated);
}
