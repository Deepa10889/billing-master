package com.deepa.billing.controllers;

import static org.mockito.Mockito.*;

import com.deepa.billing.entities.ElectricityReading;
import com.deepa.billing.services.ReadingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(SpringRunner.class)
@WebMvcTest(ElectricityReadingController.class)
public class ElectricityReadingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReadingService electricityReadingService;

    @Test
    public void testSubmitElectricityReading() throws Exception {
        // Mocking the behavior of ElectricityReadingService
        doNothing().when(electricityReadingService).submitElectricityReading(any(ElectricityReading.class));

        // Creating a sample ElectricityReading object for testing
        ElectricityReading electricityReading = new ElectricityReading();
        electricityReading.setCustomerId(1L);
        electricityReading.setCurrentReading(100);

        // Performing the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/readings/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(electricityReading)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Electricity reading submitted successfully."));
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
