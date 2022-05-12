package com.investing.securities.repository;

import com.investing.securities.model.Investment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends AbstractRepository<Investment> {

    List<Investment> findAllByCustomerId(UUID customerId);
}
