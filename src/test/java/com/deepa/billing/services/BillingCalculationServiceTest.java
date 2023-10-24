package com.deepa.billing.services;

import com.deepa.billing.entities.PriceSlab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BillingCalculationServiceTest {

    @InjectMocks
    private BillCalculationService billingCalculationService;

    @Mock
    private PriceSlabService priceSlabService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCalculateBill() {
        // Mock data
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        int currentReading = 300;
        int previousReading = 100;

        PriceSlab slab1 = new PriceSlab(1, 100, 0.5);
        PriceSlab slab2 = new PriceSlab(101, 200, 0.7);
        List<PriceSlab> priceSlabs = Arrays.asList(slab1, slab2);

        // Mocking service method
        when(priceSlabService.findPriceSlabsForPeriod(any(), any())).thenReturn(priceSlabs);

        // Calling the method to test
        double totalBill = billingCalculationService.calculateBill(startDate, endDate, currentReading, previousReading);

        // Assertions
        assertEquals(120.0, totalBill);
    }
}
