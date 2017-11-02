package com.summit.ehcache.manager;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class SampleEhcacheFactoryBean implements FactoryBean<SampleEhcache>, InitializingBean{
	
	private SampleEhcacheManager cacheManager;
	
	private String cacheName;
	
	private SampleEhcache cache;
	
	
	public void setCacheManager(SampleEhcacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	@Override
	public SampleEhcache getObject() throws Exception {
		return cache;
	}

	@Override
	public Class<? extends SampleEhcache> getObjectType() {
		return (cache != null ? cache.getClass() : SampleEhcache.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SampleEhcache sampleCache = (SampleEhcache) cacheManager.getCache(cacheName);
		if(sampleCache == null) {
			throw new Exception("no cache "+cacheName);
		}
		sampleCache.setCacheName(cacheName);
		
		this.cache = sampleCache;
	}

}
