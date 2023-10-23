package com.deepa.billing.controllers;

import com.deepa.billing.entities.PriceSlab;
import com.deepa.billing.services.BillCalculationService;
import com.deepa.billing.services.PriceSlabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/priceslabs")
public class PriceSlabController {

    @Autowired
    private PriceSlabService priceSlabService;
    private BillCalculationService billingCalculationService;

    @GetMapping("/find")
    public ResponseEntity<List<PriceSlab>> findPriceSlabs(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<PriceSlab> priceSlabs = priceSlabService.findPriceSlabsForPeriod(startDate, endDate);
        return ResponseEntity.ok(priceSlabs);
    }

    @GetMapping("/calculateBill")
    public ResponseEntity<Double> calculateBill(
            @RequestParam("currentReading") int currentReading,@RequestParam("previousReading") int previousReading,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        double billAmount = billingCalculationService.calculateBill(currentReading,previousReading,startDate,endDate);
        return ResponseEntity.ok(billAmount);
    }
    // Other endpoints related to price slabs can be added here
}

