package com.summit.ehcache.manager.interfaces;

import java.io.Serializable;


public interface SampleCache {
	String getCacheName();
	
	/**
	 * 캐시에서 Serializable 타입 데이터를 조회하는 메소드
	 * @param key 캐시 데이터를 조회할 key
	 * @return 캐시 데이터
	 * @throws Exception
	 */
	Serializable getData(Serializable key) throws Exception;
	
	/**
	 * 캐시에서 Serializable 타입 데이터를 추가/수정하는 메소드
	 * @param key 캐시에서 삭제할 데이터에 대한 key
	 * @param value 캐시에 추가/수정할 데이터
	 * @throws Exception
	 */
	void putData(Serializable key, Serializable value) throws Exception;

	/**
	 * 캐시에서 Serializable 타입 데이터를 삭제하는 메소드
	 * @param key 캐시에서 삭제할 데이터에 대한 key
	 * @throws Exception
	 */
	void removeData(Serializable key) throws Exception;
	
	Object getObjectData(Object key) throws Exception;
}
