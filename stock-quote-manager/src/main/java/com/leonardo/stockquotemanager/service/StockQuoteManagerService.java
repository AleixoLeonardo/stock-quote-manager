package com.leonardo.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.leonardo.stockquotemanager.constant.Constants;
import com.leonardo.stockquotemanager.json.NotificationJson;
import com.leonardo.stockquotemanager.json.StockJson;
import com.leonardo.stockquotemanager.json.StockMessage;

@Service
public class StockQuoteManagerService {

	@Value("${stock.manager.link}")
	private String link;

	@Value("${stock.manager.link.notify}")
	private String linkNotify;

	@Value("${server.port}")
	private Integer port;

	@Value("${server.host.url}")
	private String host;

	RestTemplate restTemplate;

	public StockQuoteManagerService() {
		if (restTemplate == null) {
			this.restTemplate = new RestTemplate();
		}
	}

	public StockMessage post(StockJson stockJson) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<StockJson> entity = new HttpEntity<StockJson>(stockJson, headers);

		try {
			this.restTemplate.exchange(link, HttpMethod.POST, entity, StockJson.class);
			return new StockMessage(Constants.CREATED_WITH_SUCCESS);
		} catch (HttpClientErrorException ex) {
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	public void notifyQuoteManager() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationJson notificationJson = new NotificationJson(host, port);
		HttpEntity<NotificationJson> entity = new HttpEntity<NotificationJson>(notificationJson, headers);
		try {
			restTemplate.exchange(linkNotify, HttpMethod.POST, entity, NotificationJson[].class);
		} catch (HttpClientErrorException ex) {
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	@Cacheable(value = "stock-cache", key = "#id", condition = "#id != null")
	public StockJson findById(String id) {
		return get().stream().filter(s -> s.getId().equals(id)).findFirst().get();
	}

	public List<StockJson> get() {
		try {
			StockJson[] result = this.restTemplate.getForObject(link, StockJson[].class);
			return Arrays.asList(result);
		} catch (HttpClientErrorException ex) {
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
