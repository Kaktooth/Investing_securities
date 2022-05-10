package com.investing.securities.service;

import com.investing.securities.model.Customer;
import com.investing.securities.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

}
