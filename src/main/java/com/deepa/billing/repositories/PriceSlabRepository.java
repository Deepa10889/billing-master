package com.deepa.billing.repositories;

import com.deepa.billing.entities.PriceSlab;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PriceSlabRepository extends JpaRepository<PriceSlab, Long> {

    List<PriceSlab> findByStartDateBeforeAndEndDateAfter(LocalDate endDate, LocalDate startDate);
}
