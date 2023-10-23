package com.deepa.billing.services;

import com.deepa.billing.entities.PriceSlab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillCalculationService {

    private final PriceSlabService priceSlabService;

    @Autowired
    public BillCalculationService(PriceSlabService priceSlabService) {
        this.priceSlabService = priceSlabService;
    }

    public double calculateBill(int currentReading, int previousReading, LocalDate startDate, LocalDate endDate) {
        int unitsConsumed = currentReading - previousReading;
        List<PriceSlab> applicablePriceSlabs= priceSlabService.findPriceSlabsForPeriod(startDate, endDate);
        double slabRate = findSlabRateForConsumption(applicablePriceSlabs, unitsConsumed);
        double billAmount = unitsConsumed * slabRate;
        return billAmount;
    }

    private double findSlabRateForConsumption(List<PriceSlab> priceSlabs, int unitsConsumed) {
        // Iterate through the slabs to find the appropriate rate

            for (PriceSlab slab : priceSlabs) {
                if (unitsConsumed >= slab.getStartUnits() && unitsConsumed <= slab.getEndUnits()) {
                    return slab.getRate();
                }
            }

        // Handle the case where no applicable slab is found
        throw new RuntimeException("No applicable price slab found for the given consumption: " + unitsConsumed);
    }

}