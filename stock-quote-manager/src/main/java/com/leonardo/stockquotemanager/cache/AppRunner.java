package com.leonardo.stockquotemanager.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.service.StockQuoteManagerService;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final StockQuoteManagerService stockQuoteManagerService;
	
	@Value("${server.port}")
	private Integer port;
	
	@Value("${server.host.url}")
	private String host;

	public AppRunner(StockQuoteManagerService stockQuoteManagerService) {
		this.stockQuoteManagerService = stockQuoteManagerService;
	}

	@Override
	public void run(String... args) throws Exception {
		StringBuilder info = new StringBuilder("");
		info.append("---Notifying QuoteManager---");
		info.append(System.getProperty("line.separator"));
		info.append("Port: ");
		info.append(port);
		info.append(System.getProperty("line.separator"));
		info.append("Host: ");
		info.append(host);
		logger.info(info.toString());
		stockQuoteManagerService.notifyQuoteManager();
		
		List<StockJson> stocks = stockQuoteManagerService.get();
		logger.info("---Caching stocks---");
		for(StockJson stockJson : stocks) {
			logger.info(stockJson.getId());
			stockQuoteManagerService.findById(stockJson.getId());
		}
	}

}
