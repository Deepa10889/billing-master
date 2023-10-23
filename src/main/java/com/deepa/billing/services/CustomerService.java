package com.deepa.billing.services;

import com.deepa.billing.entities.Customer;
import com.deepa.billing.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
       // validateCustomer(customer); // Implement customer validation logic if needed
        return customerRepository.save(customer);
    }

    // Implement additional service methods for customer retrieval, updating, and deletion if needed

    private void validateCustomer(Customer customer) {
        // Implement customer validation logic here if necessary
        // For example, check if required fields are present, validate email format, etc.
    }
}