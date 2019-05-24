package com.core.config;


import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropTestdataConfig {
	private ResourceBundle properties ;
	private HashMap<String,String> webElementMapping;
	public static final Logger logger = LogManager.getLogger(PropLocatorConfig.class);

	public PropTestdataConfig(){
		properties = ResourceBundle.getBundle("testdata");
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
