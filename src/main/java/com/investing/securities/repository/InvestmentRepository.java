package com.investing.securities.repository;

import com.investing.securities.model.Investment;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends AbstractRepository<Investment> {
}
