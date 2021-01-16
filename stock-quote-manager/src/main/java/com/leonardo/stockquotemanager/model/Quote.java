package com.leonardo.stockquotemanager.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.leonardo.stockquotemanager.json.StockJson;

@Entity
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String quoteDate;
	private Double price;

	private String stock;
	
	public Quote() {
		
	}
	
	public Quote(Map.Entry<String, String> quotes, StockJson stockJson) {
		this.toQuote(quotes, stockJson);
	}

	public String getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(String quoteDate) {
		this.quoteDate = quoteDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	private void toQuote(Map.Entry<String, String> entry, StockJson stockJson) {
		
		this.quoteDate = entry.getKey();
		this.price = Double.valueOf(entry.getValue());
		this.stock = stockJson.getId();
	}

}
