package com.leonardo.stockquotemanager.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.stockquotemanager.service.StockQuoteService;

@SpringBootTest
public class StockQuoteServiceTest {

	@Autowired
	private StockQuoteService stockQuoteService;

	@Test
	public void findByIdExists() throws Exception {
		String id = "petr3";
		assertThat(stockQuoteService.findById(id)).isNotNull();
	}
}
