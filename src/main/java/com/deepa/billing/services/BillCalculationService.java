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

    public double calculateBill(LocalDate startDate, LocalDate endDate, int currentReading, int previousReading) {
        double totalBill = 0.0;
        List<PriceSlab> priceSlabs = priceSlabService.findPriceSlabsForPeriod(startDate, endDate);
        for (PriceSlab priceSlab : priceSlabs) {
            int unitsConsumed = currentReading - previousReading;
            int slabUnits = Math.min(unitsConsumed, priceSlab.getEndUnits()) - priceSlab.getStartUnits() + 1;
            double slabBill = slabUnits * priceSlab.getRate();
            totalBill += slabBill;
            unitsConsumed -= slabUnits;
            if (unitsConsumed <= 0) {
                break;
            }
        }
        return totalBill;
    }

}