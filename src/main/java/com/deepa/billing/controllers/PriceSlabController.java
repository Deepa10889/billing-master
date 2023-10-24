package com.deepa.billing.controllers;

import com.deepa.billing.entities.PriceSlab;
import com.deepa.billing.services.BillCalculationService;
import com.deepa.billing.services.PriceSlabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/priceslabs")
public class PriceSlabController {

    @Autowired
    private PriceSlabService priceSlabService;

    @Autowired
    private BillCalculationService calculateBillService;

    @PostMapping("/addPriceSlab")
    public ResponseEntity<String> addPriceSlab(@RequestBody PriceSlab priceSlab) {
        priceSlabService.addPriceSlab(priceSlab);
        return ResponseEntity.ok("Price slab added successfully.");
    }

    @GetMapping("/getPriceSlabs")
    public ResponseEntity<List<PriceSlab>> getPriceSlabs(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<PriceSlab> priceSlabs = priceSlabService.findPriceSlabsForPeriod(startDate, endDate);
        return ResponseEntity.ok(priceSlabs);
    }

    @GetMapping("/calculateBill")
    public ResponseEntity<Double> calculateBill(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("currentReading") int currentReading,
            @RequestParam("previousReading") int previousReading) {

        double billAmount = calculateBillService.calculateBill(startDate, endDate,currentReading,previousReading);
        return ResponseEntity.ok(billAmount);
    }
}
