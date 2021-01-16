package com.leonardo.stockquotemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.stockquotemanager.model.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, String> {
	@Query("SELECT q FROM Quote q WHERE q.stock = ?1")
	List<Quote> findByStockId(String stockId);
}
