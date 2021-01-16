package com.leonardo.stockquotemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.stockquotemanager.constant.Constants;
import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.service.StockQuoteServiceValidator;

@SpringBootTest
public class StockQuoteServiceValidatorTest {

	@Autowired
	private StockQuoteServiceValidator stockQuoteServiceValidator;

	@Test
	public void invalidId() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			stockQuoteServiceValidator.isValidStockQuote(new StockJson());
		});
		assertEquals(exception.getMessage(), Constants.THE_FIELD_ID_CAN_NOT_BE_NULL);
	}

}
