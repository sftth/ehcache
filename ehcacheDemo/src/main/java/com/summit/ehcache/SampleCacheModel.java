package com.summit.ehcache;

import java.io.Serializable;

public class SampleCacheModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String val;
	
	public SampleCacheModel() {
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public String toString() {
		return "SampleCacheModel{key='"+key+"\', val='"+val+"\'}"; 
	}
}
