package com.investing.securities.repository;

import com.investing.securities.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer> {
}
