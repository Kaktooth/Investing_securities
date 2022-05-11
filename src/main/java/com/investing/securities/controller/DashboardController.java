package com.investing.securities.controller;

import com.investing.securities.model.Customer;
import com.investing.securities.model.Investment;
import com.investing.securities.model.Securities;
import com.investing.securities.repository.CustomerRepository;
import com.investing.securities.repository.InvestmentRepository;
import com.investing.securities.repository.SecuritiesRepository;
import com.investing.securities.service.AbstractService;
import com.investing.securities.service.CustomerService;
import com.investing.securities.service.InvestmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
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
    public String getDashboardModel(
        @RequestParam(value = "searchString", required = false) String searchString,
        Model model) {
        System.out.println(searchString);
        List<Customer> searchCustomers = new ArrayList<>();
        if (searchString!=null) {
            searchCustomers = ((CustomerService) customerService).searchCustomers(searchString, 4);
            System.out.println(searchCustomers);
            model.addAttribute("searchCustomers", searchCustomers);
        }

        model.addAttribute("securitiesList", securitiesService.findAll());
        model.addAttribute("investmentsList", new ArrayList<>());
        model.addAttribute("customer", new Customer());
        model.addAttribute("securities", new Securities());
        model.addAttribute("investment", new Investment());

        return "dashboard";
    }

    @GetMapping("/client/{id}")
    public String getCustomerInvestments(@PathVariable String id,
                                         Model model) {
        model.addAttribute("investments",
            ((InvestmentService) investmentService).findAllById(UUID.fromString(id)));
        return "dashboard";
    }

    @PostMapping("/create-customer")
    public String createCustomer(
        @ModelAttribute Customer customer,
        BindingResult bindingResult) {
        customerService.save(customer);
        return "redirect:/dashboard";
    }

    @PostMapping("/create-securities")
    public String createSecurities(
        @ModelAttribute Securities securities,
        BindingResult bindingResult) {
        securitiesService.save(securities);
        return "dashboard";
    }

    @PostMapping("/invest")
    public String investSecurities(
        @ModelAttribute Investment investment,
        BindingResult bindingResult) {
        investmentService.save(investment);
        return "dashboard";
    }
}
