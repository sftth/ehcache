package com.summit.ehcache.cacheView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.summit.ehcache.SampleCacheModel;
import com.summit.ehcache.manager.interfaces.SampleCache;


@Controller
public class SampleEhcacheViewController {
	private static final Logger logger = Logger.getLogger(SampleEhcacheViewController.class);
	
	@Resource(name = "sampleCache")
	SampleCache sampleCache;
	
	@RequestMapping(value = "/cacheMain.do", method = RequestMethod.GET)
	public String ehCacheMain(Locale locale, Model model) {
		logger.info("cache main is called.");

//		model.addAttribute("serverTime", formattedDate );
		
		return "cacheMain";
	}
	
	@RequestMapping(value = "/samplecacheview/{key}", method = RequestMethod.GET)
	public void ehCacheView(@PathVariable String key) {
		logger.info("Ehcache view is called.");
		try {
			Object value = sampleCache.getObjectData(key);
			logger.warn(sampleCache.getCacheName()+ " key[" + key + "] value[" + ((SampleCacheModel)value).getVal() + "]");
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping(value = "/readCache.json")
	public void ehCacheRead(SampleCacheModel sampleModel, Model model) {
		logger.info("Read Ehcache is called.");
		Object value = null;
		try {
			String key = sampleModel.getKey();
			value = sampleCache.getObjectData(key);
			logger.warn(sampleCache.getCacheName()+ " key[" + key + "] value[" + ((SampleCacheModel)value).getVal() + "]");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		model.addAttribute("cacheData",(SampleCacheModel)value);
	}
	
	@RequestMapping(value = "/putCache.json", method=RequestMethod.POST)
	public void ehCachePut(SampleCacheModel sampleModel, Model model) {
		logger.info("Put element in the Ehcache");
		try {
			String key = sampleModel.getKey();
			sampleCache.putData(key, sampleModel);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
