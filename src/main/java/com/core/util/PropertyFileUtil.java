package com.core.util;



import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Name : PropertyFileUtil
 * 
 * Description : Utility class which loads Property file and get the properties defined
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
public class PropertyFileUtil {

	ResourceBundle properties ;
	public static final Logger logger = LogManager.getLogger(PropertyFileUtil.class);
	
	public PropertyFileUtil(String propertyFileName){
		 properties = ResourceBundle.getBundle("config");
	}
	
	
	 /**
     *  Get the value of key from property file as string
     *
     * @param Key - Property key 
     * @return String - Value of key in property file
     *
     **/
	public String getString(String Key) {
		String res = null;
		try {
			res = properties.getString(Key);
		} catch (Exception Ex) {
			System.err.println("[ERROR] Expected Key " + Key + " does not exist in the properties file, returning null!");
		}
		return res;
	}

	 /**
     *  Get the value of key from property file as integer
     *
     * @param Key - Property key 
     * @return int - Value of key in property file
     *
     **/
	public int getInt(String Key) {
		try {
			return Integer.parseInt(properties.getString(Key));
		} catch (Exception x) {
			System.err.println("[ERROR] Expected Key " + Key + " does not exist in the properties file, returning 0!");
		}
		return 0;
	}

	/**
     *  Get the value of key from property file as boolean
     *
     * @param Key - Property key 
     * @return boolean - Value of key in property file
     *
     **/
	public Boolean getBoolean(String Key) throws Exception {
		try {
			return Boolean.parseBoolean(properties.getString(Key));
		} catch (Exception x) {
			System.err.println("[ERROR] Expected Key " + Key + " does not exist in the properties file, returning null!");
		}
		return null;
	}
}

