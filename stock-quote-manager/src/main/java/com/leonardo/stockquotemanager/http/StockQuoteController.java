package com.leonardo.stockquotemanager.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.json.StockMessage;
import com.leonardo.stockquotemanager.service.StockQuoteService;

@RestController
public class StockQuoteController {

	@Autowired
	private StockQuoteService stockQuoteService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<StockMessage> create(@RequestBody StockJson stockJson) {
		return new ResponseEntity<StockMessage>(stockQuoteService.create(stockJson), HttpStatus.OK);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<StockJson>> findAll() {
		return new ResponseEntity<List<StockJson>>(stockQuoteService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<StockJson> findById(@PathVariable String id) {
		try {
			return new ResponseEntity<StockJson>(stockQuoteService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
