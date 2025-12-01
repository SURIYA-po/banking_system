package com.bank.customer_service.service.impl;

import com.bank.customer_service.entity.Customer;
import com.bank.customer_service.repository.CustomerRepository;
import com.bank.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Override
    public Customer register(Customer customer) {

        if (repo.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        return repo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer updated) {
        Customer customer = getCustomerById(id);

        customer.setFullName(updated.getFullName());
        customer.setEmail(updated.getEmail());
        customer.setPhone(updated.getPhone());
        customer.setAddress(updated.getAddress());
        customer.setNationalId(updated.getNationalId());

        return repo.save(customer);
    }
}
