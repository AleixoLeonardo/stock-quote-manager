package com.leonardo.stockquotemanager.json;

import java.util.Map;

public class StockJson {

	private String id;

	private String description;

	private Map<String, String> quotes;

	public StockJson() {

	}

	public StockJson(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, String> quotes) {
		this.quotes = quotes;
	}

}