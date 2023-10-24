package com.deepa.billing.services;


import com.deepa.billing.entities.PriceSlab;
import com.deepa.billing.repositories.PriceSlabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PriceSlabService {

    private final PriceSlabRepository priceSlabRepository;

    @Autowired
    public PriceSlabService(PriceSlabRepository priceSlabRepository) {
        this.priceSlabRepository = priceSlabRepository;
    }

    public List<PriceSlab> findPriceSlabsForPeriod(LocalDate startDate, LocalDate endDate) {
        return priceSlabRepository.findByStartDateBeforeAndEndDateAfter(endDate, startDate);
    }

    public void addPriceSlab(PriceSlab priceSlab) {
        priceSlabRepository.save(priceSlab);
    }

}

