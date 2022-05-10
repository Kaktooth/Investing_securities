package com.investing.securities.service;

import com.investing.securities.model.Investment;
import com.investing.securities.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class InvestmentService extends AbstractService<Investment, InvestmentRepository> {

    public InvestmentService(InvestmentRepository repository) {
        super(repository);
    }

    public List<Investment> findAllById(UUID id) {
        return repository.findAllById(Collections.singleton(id));
    }
}
