package com.leonardo.stockquotemanager.json;

public class StockMessage {
	private String message;

	public StockMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}

}
