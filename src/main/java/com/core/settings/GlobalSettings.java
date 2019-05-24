package com.core.settings;




import java.util.EnumMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.core.config.BasicConfig;
import com.core.config.PropTestdataConfig;
import com.core.util.PropertyFileUtil;
/**
 * Name:GlobalSettings
 * 
 * Description:Global Settings is the class where it load project configuration file and set the basic setting like os, baseurl, browser.. 
 * Author: Rajesh
 *
 *
 */
public class GlobalSettings implements BasicConfig {

	
	//Settings

	private static int defaultTimeout = 10000;
	private static boolean started = false;
	private static osList os;
	private static EnumMap<selectSite, String> homePages = new EnumMap<selectSite, String>(selectSite.class);
	private static selectSite currentlySelectedSite;
	private static String browser;
	private static String siteSubDomain;
	private static String siteDomain;
	public static final String sepReg = "(\\\\|/)";
	private static boolean checkHomepageLoaded;
	private static WebElement homepageElement;
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
	private PropTestdataConfig propUtils = new PropTestdataConfig();
	public static final Logger logger = LogManager.getLogger(GlobalSettings.class);
	private static String csvTestDataFile;	    
	private static String chromeDriverLocation;
	private static String ieDriverLocation;
	private static String firefoxDriverLocation;
	private static String  csvfileLocation;
	private static  String FILE_PATH;
	
	
	public GlobalSettings() {
		
		setOS(propUtil.getString("os"));
		if (!setBrowser(propUtil.getString("browser"))) {
			System.err.println("Invalid Browser Setting Detected!");
		}
		setChromeDriverLocation(propUtil.getString("chromeDriverLocation"));
		setIeDriverLocation(propUtil.getString("ieDriverLocation"));
		setFirefoxDriverLocation(propUtil.getString("firefoxDriverLocation"));
		setHomePages();   
	}

	
	public String getCsvTestDataFile() {
		setCsvTestDataFile(propUtil.getString("region"));
			return csvTestDataFile;
	}

	private void setCsvTestDataFile(String csvTestDataFile) {
		propUtil.getString(getCsvTestDataFile());
		GlobalSettings.csvTestDataFile = csvTestDataFile;
	}
	
		

	private   void setHomePages() {
		for (selectSite page : selectSite.values()) {
			//If command line parameters are not present
			if(System.getProperty("admin_home")!=null){
				homePages.put(page, System.getProperty("euro_home"));
			}
			/*else if(
				System.getProperty("admin_home")!=null){
					homePages.put(page, System.getProperty("us_home"));
			}
			else if(
					System.getProperty("admin_home")!=null){
					homePages.put(page, System.getProperty("india_home"));
			}*/
			

			homePages.put(page, propUtil.getString(page.toString().toLowerCase()).trim());
		}
	}
	
	

	public String getHomePage(selectSite page) {
		return homePages.get(page);
	}

	
	
	
	
	public void defaultTimeout(int value) {
		defaultTimeout = value;
	}


	 /**
     * Set the os via Enum
     *
     * @param value
     */
	public boolean setOS(String value) {
		for (osList osVal : osList.values()) {
			if (osVal.toString().toLowerCase().equals(value)) {
				os = osVal;
				return true;
			}
		}
		return false;
	}
	public osList getOS() {
		return os;
	}

	
	/**
     * Set the selected/default browser via Enum
     *
     * @param value
     */
	private boolean setBrowser(String value) {
		for (selectedBrowser browserVal : selectedBrowser.values()) {
			if (browserVal.toString().toLowerCase().equals(value)) {
				browser = browserVal.toString().toLowerCase();
				return true;
			}
		}
		return false;
	}

	public String getBrowser() {
		return browser;
	}

	public boolean started() {
		return started;
	}

	public void started(boolean value) {
		started = value;
	}
	
	 /**
     * Takes the site url set in the properties file and splits it out into domain and subdomain
     * {This assume that all domains are two part i.e. foo.com.  This will not work for a hostname i.e. localhost)
     *
     * @param site
     */
	public void setCurrentlySelectedSite(selectSite site) {
		currentlySelectedSite = site;
		String[] urlComponents = homePages.get(currentlySelectedSite).split("\\.");
		siteDomain = urlComponents[urlComponents.length - 2] + "." + urlComponents[urlComponents.length - 1];
		siteSubDomain = urlComponents[0];
		for (int i = 1; i < urlComponents.length - 2; i++) {
			siteSubDomain += "." + urlComponents[i];
		}
	}

	
	public int defaultTimeout() {
		return defaultTimeout;
	}


	/**
	 * @return the ieDriverLocation
	 */
	public static String firefoxDriverLocation() {
		return firefoxDriverLocation;
	}
	/**
	 * @param chromeDriverLocation the chromeDriverLocation to set
	 */
	public static void setFirefoxDriverLocation(String firefoxDriverExe) {
		System.setProperty("webdriver.gecko.driver", firefoxDriverExe.replace("//", "////"));
		firefoxDriverLocation = firefoxDriverExe;
	}

	
	/**
	 * @return the chromeDriverLocation
	 */
	public static String getChromeDriverLocation() {
		return chromeDriverLocation;
	}

	
	/**
	 * @param chromeDriverLocation the chromeDriverLocation to set
	 */
	public static void setChromeDriverLocation(String chromeDriverExe) {
		System.setProperty("webdriver.chrome.driver", chromeDriverExe.replace("//", "////"));
		chromeDriverLocation = chromeDriverExe;
	}


	/**
	 * @return the ieDriverLocation
	 */
	public static String getIeDriverLocation() {
		return ieDriverLocation;
	}


	/**
	 * @param ieDriverLocation the ieDriverLocation to set
	 */
	public static void setIeDriverLocation(String ieDriverExe) {
		System.setProperty("webdriver.ie.driver", ieDriverExe.replace("//", "////"));
		ieDriverLocation = ieDriverExe;
	}


	
    	public String siteSubDomain() {
		return siteSubDomain;
	}

	public String siteDomain() {
		return siteDomain;
	}
	/*
	public selectSite getCurrentlySelectedSite() {
		return currentlySelectedSite;
	}

	private void setCheckHomepageLoaded(boolean value) {
		checkHomepageLoaded = value;
	}

	public boolean checkHomepageLoaded() {
		return checkHomepageLoaded;
	}

	public void setHomepageElement(WebElement element) {
		homepageElement = element;
		setCheckHomepageLoaded(true);
	}

	public void clearHomepageElement() {
		homepageElement = null;
		setCheckHomepageLoaded(false);
	}

	public WebElement getHomepageElement() {
		return homepageElement;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}