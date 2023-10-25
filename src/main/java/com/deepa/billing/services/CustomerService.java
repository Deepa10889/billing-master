package com.deepa.billing.services;

import com.deepa.billing.entities.Customer;
import com.deepa.billing.repositories.CustomerRepository;
import com.deepa.billing.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        if (!ValidationUtils.isValidEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        // Validate if the email is not already registered
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email address is already registered");
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        return customerRepository.save(existingCustomer);
    }

}