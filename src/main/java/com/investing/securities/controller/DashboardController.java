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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    private void initObjects(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("securities", new Securities());
        model.addAttribute("investment", new Investment());
    }

    @GetMapping
    public String getDashboardModel(
        @RequestParam(value = "searchString", required = false) String searchString,
        @RequestParam(value = "customerId", required = false) String customerId,
        RedirectAttributes attributes,
        Model model) {
        initObjects(model);
        if (searchString != null) {
            List<Customer> searchCustomers = ((CustomerService) customerService).searchCustomers(searchString, 4);
            model.addAttribute("searchCustomers", searchCustomers);
        }
        List<Securities> securities = securitiesService.findAll();
        model.addAttribute("securitiesList", securities);
        model.addAttribute("customersList", customerService.findAll());
        model.addAttribute("investmentsList", new ArrayList<>());

        return "dashboard";
    }

    @GetMapping("/customer/{customerId}")
    public String getCustomerInvestments(@PathVariable String customerId,
                                         RedirectAttributes attributes,
                                         Model model) {
        attributes.addFlashAttribute("customer", new Customer());
        attributes.addFlashAttribute("securities", new Securities());
        attributes.addFlashAttribute("investment", new Investment());
        Customer currentCustomer = customerService.findById(UUID.fromString(customerId));
        attributes.addFlashAttribute("currentCustomer", currentCustomer);
        attributes.addAttribute("currentCustomer", currentCustomer);
        attributes.addFlashAttribute("customerId", customerId);
        attributes.addAttribute("customerId", customerId);
        Map<Investment, Securities> investments = ((InvestmentService) investmentService).findAllById(
            UUID.fromString(customerId), securitiesService);
        System.out.println(investments.toString());
        attributes.addFlashAttribute("investments", investments);
        attributes.addFlashAttribute("localDateTime", LocalDateTime.now());
        attributes.addFlashAttribute("date", new Date());

        return "redirect:/dashboard" + "?currentCustomer={currentCustomer}&customerId={customerId}";
    }

    @GetMapping("/customer-log-out")
    public String getCustomerInvestments(
        RedirectAttributes attributes,
        Model model) {
        attributes.addFlashAttribute("currentCustomer", null);
        return "redirect:/dashboard";
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
        RedirectAttributes attributes,
        BindingResult bindingResult,
        Model model) {
        securitiesService.save(securities);
        return "redirect:/dashboard";
    }

    @PostMapping("/invest/{customerId}")
    public String investSecurities(
        @PathVariable String customerId,
        @ModelAttribute Investment investment,
        @ModelAttribute Date date,
        RedirectAttributes attributes,
        BindingResult bindingResult,
        Model model) {

        investment.setDateOfPurchase(LocalDateTime.now());
        investment.setDateOfSale(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        investment.setCustomerId(UUID.fromString(customerId));
        System.out.println(investment);
        investmentService.save(investment);
        return "redirect:/dashboard";
    }
}
