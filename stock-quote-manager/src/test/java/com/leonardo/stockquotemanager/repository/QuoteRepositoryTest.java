package com.leonardo.stockquotemanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.stockquotemanager.model.Quote;

@SpringBootTest
public class QuoteRepositoryTest {

	@Autowired
	private QuoteRepository quoteRepository;

	@Test
	public void addQuote() {
		Quote quote = new Quote();
		quote.setPrice(50.0);
		quote.setStock("unit");
		quote.setQuoteDate("2019-01-04");
		assertThat(quoteRepository.save(quote)).isNotNull();
	}
}
