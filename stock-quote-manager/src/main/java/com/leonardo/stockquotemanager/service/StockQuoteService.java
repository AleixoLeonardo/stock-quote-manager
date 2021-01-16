package com.leonardo.stockquotemanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.stockquotemanager.constant.Constants;
import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.json.StockMessage;
import com.leonardo.stockquotemanager.model.Quote;
import com.leonardo.stockquotemanager.repository.QuoteRepository;

@Service
public class StockQuoteService {


	@Autowired
	private StockQuoteServiceValidator stockQuoteServiceValidator;

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private StockQuoteManagerService stockQuoteManagerService;

	public StockMessage create(StockJson stockJson) {
		try {
			stockQuoteServiceValidator.isValidStockQuote(stockJson);
			stockQuoteManagerService.post(stockJson);
			saveQuote(stockJson);

			return new StockMessage(Constants.CREATED_WITH_SUCCESS);

		} catch (Exception e) {
			return new StockMessage(e.getMessage());
		}

	}

	private void saveQuote(StockJson stockJson) {
		for (Map.Entry<String, String> entry : stockJson.getQuotes().entrySet()) {
			quoteRepository.save(new Quote(entry, stockJson));
		}
	}

	public List<StockJson> findAll() {
		List<StockJson> listStockJson = stockQuoteManagerService.get();
		for (StockJson stockJson : listStockJson) {
			stockJson.setQuotes(quoteToMap(quoteRepository.findByStockId(stockJson.getId())));
		}
		return listStockJson;
	}

	public StockJson findById(String id) throws Exception {
		try {
			StockJson stockJson = stockQuoteManagerService.findById(id);
			stockJson.setQuotes(quoteToMap(quoteRepository.findByStockId(id)));
			return stockJson;
		} catch (NoSuchElementException e) {
			throw new Exception(Constants.STOCK_NOT_FOUND);
		}

	}

	private Map<String, String> quoteToMap(List<Quote> listQuote) {
		Map<String, String> mapList = new HashMap<>();
		if (listQuote != null) {
			for (Quote quote : listQuote) {
				mapList.put(quote.getQuoteDate(), quote.getPrice().toString());
			}
		}
		return mapList;
	}

}
