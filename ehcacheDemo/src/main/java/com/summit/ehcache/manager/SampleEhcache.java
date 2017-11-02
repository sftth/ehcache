package com.summit.ehcache.manager;

import java.io.Serializable;

import javax.lang.model.util.Elements;

import com.summit.ehcache.manager.interfaces.SampleCache;

import net.sf.ehcache.Ehcache;

import net.sf.ehcache.Element;

public class SampleEhcache implements SampleCache{
	private final Ehcache ehcache;
	
	private String cacheName = "";
	
	
	public SampleEhcache(Ehcache ehcache) {
		this.ehcache = ehcache;
	}
	
	
	public String getCacheName() {
		return cacheName;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public Serializable getData(Serializable key) throws Exception{
		return getDataPrivate(key);
	}
	
	private Serializable getDataPrivate(Serializable key) throws Exception {
		Serializable result = null;
		try {
			Element element = ehcache.get(key);
			if (element != null) {
				result = element.getValue();
			}
			
		} catch (Exception e) {
			throw e;
			
		}
		return result;
	}
	
	public void putData(Serializable key, Serializable value) throws Exception{
		try {
			ehcache.put(new Element(key, value));
		} catch (Exception e) {
			throw e;
			
		}
	}
	
	public void removeData(Serializable key) throws Exception{
		try {
			ehcache.remove(key);
		} catch (Exception e) {
			throw e;
			
		}
	}


	@Override
	public Object getObjectData(Object key) throws Exception {
		Object result = null;
		try {
			Element element = ehcache.get(key);
			if(element != null) {
				result = element.getObjectValue();
			}
		} catch (Exception e) {
			throw new Exception("getObejctData error");
		}
		return result;
	}
}
