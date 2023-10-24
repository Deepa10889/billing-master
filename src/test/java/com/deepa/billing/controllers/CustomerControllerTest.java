package com.deepa.billing.controllers;


import static org.mockito.Mockito.*;

import com.deepa.billing.entities.Customer;
import com.deepa.billing.services.CustomerService;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testRegisterCustomer_EmailAlreadyExists() throws Exception {
        // Mocking the registration response with a RuntimeException
        when(customerService.registerCustomer(any(Customer.class)))
                .thenThrow(new RuntimeException("Email address is already registered"));

        // Performing the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"www@gmail.com\",\"name\":\"John Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Email address is already registered"));
    }

    @Test
    public void testRegisterCustomer() throws Exception {
        // Mocking a customer object
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");

        // Mocking the registration response
        when(customerService.registerCustomer(any(Customer.class))).thenReturn(customer);

        // Performing the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Customer registered successfully with ID: 1"));
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
