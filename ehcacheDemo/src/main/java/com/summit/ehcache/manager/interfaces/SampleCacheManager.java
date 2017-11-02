package com.summit.ehcache.manager.interfaces;

import java.util.Set;


public interface SampleCacheManager {
	SampleCache getCache(String cacheName);
	
	Set<String> getCacheNameSet();
}
