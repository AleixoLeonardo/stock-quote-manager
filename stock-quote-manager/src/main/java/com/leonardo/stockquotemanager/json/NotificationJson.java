package com.leonardo.stockquotemanager.json;

public class NotificationJson {
	private String host;
	private Integer port;

	public NotificationJson() {
		
	}
	
	public NotificationJson(String host, Integer port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
