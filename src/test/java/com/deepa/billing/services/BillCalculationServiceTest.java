package com.deepa.billing.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.deepa.billing.entities.PriceSlab;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class BillCalculationServiceTest {

    @InjectMocks
    private BillCalculationService billingCalculationService;

    @Mock
    private PriceSlabService priceSlabService;

    @Test
    public void testCalculateBill() {
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
