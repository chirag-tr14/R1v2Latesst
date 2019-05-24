package com.core.config;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Name : BrowserConfig
 * 
 * Description : This class in used for managing the browser details and configuration of the browser
 * 
 * 
 *
 * Version : 1.0
 */

public class BrowserConfig implements BasicConfig{

	private selectedBrowser browser;
	private String chromeDriverLocation;
	private String firefoxDriverLocation;
	private String ieDriverLocation;
	public static final Logger logger = LogManager.getLogger(BrowserConfig.class);
	
	public BrowserConfig() {
		setBrowser(selectedBrowser.FIREFOX);
	}

	public BrowserConfig(String browser) throws Exception {
		setBrowser(browser);
		firefoxDriverLocation=System.getProperty("firefoxDriverLocation");
		chromeDriverLocation=System.getProperty("chromeDriverLocation");
		ieDriverLocation=System.getProperty("ieDriverLocation");	
	}

	private void setBrowser(selectedBrowser value) {
		this.browser = value;
	}

	
	 /**
     * Set selected browser via Enum
     *
     * @param value
     */
	public void setBrowser(String value) throws Exception {
		if(System.getProperty("browser")!=null){
			for (selectedBrowser browser : selectedBrowser.values()) {
				if (browser.toString().toLowerCase().equals(System.getProperty("browser"))) {
					setBrowser(browser);
					return;
				}
		}}
		else{
		for (selectedBrowser browser : selectedBrowser.values()) {
			if (browser.toString().toLowerCase().equals(value.toLowerCase())) {
				setBrowser(browser);
				return;
			}
		}
		}
		throw new Exception("'" + value + "' is an unknown browser type!");
	}

	public selectedBrowser getBrowser() {
		return this.browser;
	}

	public void setfirefoxDriverLocation(String value) {
		this.firefoxDriverLocation = value.replaceAll("/", File.separator);
	}
	public String getfirefoxDriverLocation() {
		return this.firefoxDriverLocation;
	}
	
	
	
	
	 /**
     * Set the location of the chromedriver binaries
     *
     * @param value
     */
	public void setChromeDriverLocation(String value) {
		this.chromeDriverLocation = value.replaceAll("/", File.separator);
	}

	public String getChromeDriverLocation() {
		return this.chromeDriverLocation;
	}

	 /**
     * Set the location of the IEDriver binaries
     *
     * @param value
     */
	public void setIEDriverLocation(String value) {
		this.ieDriverLocation = value.replaceAll("/", File.separator);
	}

	public String getIEDriverLocation() {
		return this.ieDriverLocation;
	}

}
