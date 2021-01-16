package com.leonardo.stockquotemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
	@Autowired
	private CacheManager cacheManager;

	public void clearCacheStock() {
		cacheManager.getCache("stock-cache");
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}
}
