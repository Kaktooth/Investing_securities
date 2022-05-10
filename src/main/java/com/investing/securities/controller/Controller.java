package com.investing.securities.controller;

import com.investing.securities.model.Customer;
import com.investing.securities.model.Investment;
import com.investing.securities.model.Securities;
import com.investing.securities.service.CustomerService;
import com.investing.securities.service.InvestmentService;
import com.investing.securities.service.SecuritiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/controller")
public class Controller {

    private final SecuritiesService securitiesService;
    private final InvestmentService investmentService;
    private final CustomerService customerService;

    public Controller(SecuritiesService securitiesService, InvestmentService investmentService, CustomerService customerService) {
        this.securitiesService = securitiesService;
        this.investmentService = investmentService;
        this.customerService = customerService;
    }

    @PostMapping("/saveSecurities")
    public ResponseEntity<Securities> saveSecurities(
           @RequestBody Securities obj) {
        Securities securities = securitiesService.save(obj);
        return new ResponseEntity<>(securities, HttpStatus.CREATED);
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(
            @RequestBody Customer obj) {
        Customer customer = customerService.save(obj);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/saveInvestment")
    public ResponseEntity<Investment> saveInvestment(
            @RequestBody Investment obj) {
        Investment investment = investmentService.save(obj);
        return new ResponseEntity<>(investment, HttpStatus.CREATED);
    }

    @GetMapping("/getInvestment")
    public ResponseEntity<Investment> getInvestment(@RequestParam UUID id) {
        Investment investment = investmentService.findById(id);
        return new ResponseEntity<>(investment, HttpStatus.OK);
    }
}
