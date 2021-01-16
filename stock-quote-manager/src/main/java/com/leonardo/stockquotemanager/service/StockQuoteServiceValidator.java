package com.leonardo.stockquotemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.stockquotemanager.constant.Constants;
import com.leonardo.stockquotemanager.json.StockJson;

@Service
public class StockQuoteServiceValidator {

	@Autowired
	private StockQuoteManagerService stockQuoteManagerService;

	public void isValidStockQuote(StockJson stockJson) throws Exception {
		if (null == stockJson.getId()) {
			throw new Exception(Constants.THE_FIELD_ID_CAN_NOT_BE_NULL);
		}
		stockQuoteManagerService.findById(stockJson.getId());
	}
}
