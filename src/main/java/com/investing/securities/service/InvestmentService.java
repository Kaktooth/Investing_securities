package com.investing.securities.service;

import com.investing.securities.model.Investment;
import com.investing.securities.model.Securities;
import com.investing.securities.repository.InvestmentRepository;
import com.investing.securities.repository.SecuritiesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InvestmentService extends AbstractService<Investment, InvestmentRepository> {

    public InvestmentService(InvestmentRepository repository) {
        super(repository);
    }

    public List<Investment> findAllById(UUID id) {
        return repository.findAllByCustomerId(id);
    }

    public Map<Investment, Securities> findAllById(
        UUID id, AbstractService<Securities, SecuritiesRepository> service){
        List<Investment> investments = findAllById(id);
        Map<Investment, Securities> investmentsMap = new HashMap<>();
        for (var investment : investments) {
            investmentsMap.put(investment, service.findById(investment.getSecuritiesId()));
        }

        return investmentsMap;
    }
}
