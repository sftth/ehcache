package com.summit.ehcache.manager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;

import com.summit.ehcache.manager.interfaces.SampleCacheManager;

import net.sf.ehcache.CacheManager;

public class SampleEhcacheManager implements SampleCacheManager, InitializingBean{
	
	/**
	 * 캐시명과 캐시객체를 쌍으로 하여 보관하는 Map
	 */
	private final Map<String, SampleEhcache> cacheMap = new ConcurrentHashMap<String, SampleEhcache>();

	/**
	 * 내부에서 사용하는 Ehcache의 CacheManager 객체
	 */
	private CacheManager cacheManager;
	
	/**
	 * 캐시설정정보 로딩 완료여부
	 */
	private boolean cacheDefinitionLoaded = false;
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	/**
	 * InitializingBean 인터페이스의 구현 메소드<br />
	 * 스프링 컨테이너에 의해 객체가 생성된 후, 실행되는 메소드<br />
	 * - cacheManager에서 Ehcache 객체를 추출하여 Ehcache 객체와 notifyProvider를 파라미터로 SGPEhcache 객체를 생성한다.<br />
	 * - 캐시명과 SGPEhcache 객체를 cacheMap에 보관한다.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet ");
		if(!cacheDefinitionLoaded) {
			if(cacheManager != null) {
				String[] cacheNames = cacheManager.getCacheNames();
				for(String cacheName : cacheNames) {
					cacheMap.put(cacheName, new SampleEhcache(cacheManager.getEhcache(cacheName)));
					System.out.println("cache definition[" + cacheName + "] loading complete.");
				}
			}
			cacheDefinitionLoaded = true;
		}
	}
	
	/**
	 * cacheName에 해당하는 SampleCache 타입 객체를 반환하는 메소드
	 * @param cacheName
	 * @return cacheName에 해당하는 SampleCache 타입 객체
	 */
	public SampleEhcache getCache(String cacheName) {
		return (cacheName != null) ? cacheMap.get(cacheName) : null;
	}
	
	/**
	 * 모든 캐시들의 캐시명을 반환하는 메소드
	 * @return 모든 캐시들의 캐시명을 담은 set
	 */
	public Set<String> getCacheNameSet() {
		return cacheMap.keySet();
	}

}
