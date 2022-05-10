package com.investing.securities.repository;

import com.investing.securities.model.Securities;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuritiesRepository extends AbstractRepository<Securities> {
}
