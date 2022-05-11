package com.investing.securities.service;

import com.investing.securities.model.Customer;
import com.investing.securities.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> searchCustomers(String name, Integer count) {
        return findAll().stream()
            .filter(customer -> customer.getName().contains(name))
            .limit(count).toList();
    }
}
