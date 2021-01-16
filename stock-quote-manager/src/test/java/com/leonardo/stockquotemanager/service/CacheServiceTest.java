package com.leonardo.stockquotemanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import com.leonardo.stockquotemanager.service.CacheService;

@SpringBootTest
public class CacheServiceTest {
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Test
	public void checkIfCacheIsClear() {
		cacheService.clearCacheStock();
		cacheManager.getCache("stock-cache");
	}
}
