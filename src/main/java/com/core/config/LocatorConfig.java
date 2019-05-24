package com.core.config;


/**
 * Name : ControlObject
 * 
 * Description : It is an interface to be implemented to handle locators 
 * 
 * 
 * 
 * Version:1.0
 */

import java.util.HashMap;

public interface LocatorConfig {

	public static String locators = "/locators.properties";
		
	
	/**
     * Load the data from locators file and return as key, value pairs in a map.
     *
     * @return HashMap - Locators, key : value pairs
     */
	public HashMap<String, String> getWebElementMapping();
	
	
	

}
