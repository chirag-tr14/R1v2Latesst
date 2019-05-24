package com.core.config;
/**
 * Name : PropertyLocatorConfig
 * 
 * Description : This class is an implementer class of Locator config, for property file.
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropLocatorConfig  {

	private ResourceBundle properties ;
	private HashMap<String,String> webElementMapping;
	public static final Logger logger = LogManager.getLogger(PropLocatorConfig.class);

	public PropLocatorConfig(){
		properties = ResourceBundle.getBundle("locators");
	}
	
	
	public HashMap<String, String> getWebElementMapping() {
		webElementMapping = new HashMap<String,String>();
		loadProperties();
		return webElementMapping;
	}

	private void loadProperties(){
		for(String key : properties.keySet())
			webElementMapping.put(key, properties.getString(key));
	}

	
}
