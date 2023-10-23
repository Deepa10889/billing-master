package com.deepa.billing.repositories;

import com.deepa.billing.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom queries if needed
}