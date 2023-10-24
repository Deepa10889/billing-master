package com.deepa.billing.repositories;

import com.deepa.billing.entities.ElectricityReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectricityReadingRepository extends JpaRepository<ElectricityReading, Long> {
    List<ElectricityReading> findByCustomerId(Long customerId);
}