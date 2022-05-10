package com.investing.securities.service;

import com.investing.securities.model.Securities;
import com.investing.securities.repository.SecuritiesRepository;
import org.springframework.stereotype.Service;

@Service
public class SecuritiesService extends AbstractService<Securities, SecuritiesRepository> {

    public SecuritiesService(SecuritiesRepository repository) {
        super(repository);
    }
}
