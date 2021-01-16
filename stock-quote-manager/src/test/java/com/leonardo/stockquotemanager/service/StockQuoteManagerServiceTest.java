package com.leonardo.stockquotemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.leonardo.stockquotemanager.constant.Constants;
import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.service.CacheService;
import com.leonardo.stockquotemanager.service.StockQuoteManagerService;

@SpringBootTest
public class StockQuoteManagerServiceTest {

	@Autowired
	private StockQuoteManagerService stockQuoteManagerService;
	
	@Autowired
	CacheService cacheService;

	@Value("${stock.manager.link}")
	private String link;

	@Test
	public void createNewStockSuccess() {
		RestTemplate restTemplate = mock(RestTemplate.class);
		
		stockQuoteManagerService.setRestTemplate(restTemplate);
		
		StockJson stockJsonMock = new StockJson("petr30", "unit test");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<StockJson> entity = new HttpEntity<StockJson>(stockJsonMock, headers);

		when(restTemplate.exchange(link, HttpMethod.POST, entity, StockJson.class))
		.thenReturn(new ResponseEntity<StockJson>(stockJsonMock, HttpStatus.OK));
		
		assertEquals(stockQuoteManagerService.post(stockJsonMock).toString(),
				Constants.CREATED_WITH_SUCCESS);
	}

	@Test
	public void checkSizeTwoOrMore() {
		stockQuoteManagerService.setRestTemplate(new RestTemplate());
		assertTrue(stockQuoteManagerService.get().size() >= 2);
	}

	@Test
	public void getCalledOnce() {
		RestTemplate restTemplate = mock(RestTemplate.class);
		StockJson[] stockJsonMock = { new StockJson("petr3", "unitTest") };
		stockQuoteManagerService.setRestTemplate(restTemplate);
		when(restTemplate.getForObject(link, StockJson[].class)).thenReturn(stockJsonMock);
		cacheService.clearCacheStock();
		stockQuoteManagerService.findById("petr3");
		stockQuoteManagerService.findById("petr3");
		verify(restTemplate, times(1)).getForObject(link, StockJson[].class);
	}

}
