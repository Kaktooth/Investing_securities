package com.investing.securities.controller;

import com.investing.securities.model.Customer;
import com.investing.securities.model.Investment;
import com.investing.securities.model.Securities;
import com.investing.securities.repository.CustomerRepository;
import com.investing.securities.repository.InvestmentRepository;
import com.investing.securities.repository.SecuritiesRepository;
import com.investing.securities.service.AbstractService;
import com.investing.securities.service.InvestmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private final AbstractService<Customer, CustomerRepository> customerService;
    private final AbstractService<Investment, InvestmentRepository> investmentService;
    private final AbstractService<Securities, SecuritiesRepository> securitiesService;

    public DashboardController(
        AbstractService<Customer, CustomerRepository> customerService,
        AbstractService<Investment, InvestmentRepository> investmentService,
        AbstractService<Securities, SecuritiesRepository> securitiesService) {

        this.customerService = customerService;
        this.investmentService = investmentService;
        this.securitiesService = securitiesService;
    }


    @GetMapping
    public ResponseEntity<String> getDashboardModel(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("securities", securitiesService.findAll());
        model.addAttribute("investments", new ArrayList<>());
        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Page loaded: %s", "dashboard"));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<String> getCustomerInvestments(@PathVariable String id,
                                                         Model model) {
        model.addAttribute("investments",
            ((InvestmentService) investmentService).findAllById(UUID.fromString(id)));
        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Page loaded: %s", "dashboard"));
    }

    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(
        @ModelAttribute Customer customer,
        BindingResult bindingResult) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Customer created: %s", "dashboard"));
    }

    @PostMapping("/create-securities")
    public ResponseEntity<String> createSecurities(
        @ModelAttribute Securities securities,
        BindingResult bindingResult) {
        securitiesService.save(securities);
        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Securities created: %s", "dashboard"));
    }

    @PostMapping("/invest")
    public ResponseEntity<String> investSecurities(
        @ModelAttribute Investment investment,
        BindingResult bindingResult) {
        investmentService.save(investment);
        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Invest securities: %s", "dashboard"));
    }
}
