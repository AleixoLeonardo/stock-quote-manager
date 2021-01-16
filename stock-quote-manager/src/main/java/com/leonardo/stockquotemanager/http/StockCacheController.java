package com.leonardo.stockquotemanager.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.stockquotemanager.json.StockMessage;
import com.leonardo.stockquotemanager.service.CacheService;

@RestController
public class StockCacheController {
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping(path = "/stockcache", method = RequestMethod.DELETE)
	public ResponseEntity<StockMessage> clearStockCache() {
		cacheService.clearCacheStock();
		return new ResponseEntity<StockMessage>(new StockMessage("Cache cleaned!"), HttpStatus.OK);
	}

}
