package com.deepa.billing.repositories;

import com.deepa.billing.entities.ElectricityReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricityReadingRepository extends JpaRepository<ElectricityReading, Long> {
    // Custom queries if needed
}