package com.deepa.billing.controllers;
import com.deepa.billing.entities.Customer;
import com.deepa.billing.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "register customer", response = ResponseEntity.class)
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity registerCustomer(@RequestBody Customer body){
        if(body!=null) {
            customerService.registerCustomer(body);
            return new ResponseEntity("Customer registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}