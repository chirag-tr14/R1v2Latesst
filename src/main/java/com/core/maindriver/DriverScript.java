package com.core.maindriver;

import java.io.File;
import java.net.URL;
import java.nio.channels.NetworkChannel;
import java.util.Collections;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.core.config.BasicConfig;
import com.core.config.BrowserConfig;
import com.core.settings.GlobalSettings;

/**
 * Name:		Driver script
 * 
 * Description: This class is the Core class, main driver script (where we launch/start our browser/webdriver instance).
 *
 *
 * Version:		1.1
 */

public class DriverScript implements BasicConfig {

	public static GlobalSettings settings;
	public static final Logger logger = LogManager.getLogger(DriverScript.class);
	private BrowserConfig browserDetails;
	public static ChromeDriverService service;
	public static InternetExplorerDriverService ieservice;
	DesiredCapabilities capabilities;

	protected WebDriver driver;


	public DriverScript() {
		settings = new GlobalSettings();
		browserDetails = new BrowserConfig();

		startSelenium();
	}

	public DriverScript(BrowserConfig browser) {
		browserDetails = browser;
		settings = new GlobalSettings();
		startSelenium();
	}

	public WebDriver getDriverObject() {
		if(driver == null)
			startSelenium();
		return driver;
	}


	/**
	 * Starts the WebDriver Instance
	 */
	public void startSelenium() {
		if (driver != null) {
			logger.error("There appears to be an existing driver instance.. ");
			logger.error("Shutting down existing instance and starting up again...");
			stopSelenium(driver);
		}
		driver = setBrowser(driver);
		logger.info("Opened browser");

	}


	/**
	 * Set the driver type based upon gobal settings
	 * 
	 * @param driverObject - object to instantiate
	 * @return WebDriver
	 */
	public WebDriver setBrowser(WebDriver driverObject) {
		try {

			switch (browserDetails.getBrowser()) {
			case FIREFOXREMOTE:
				capabilities = DesiredCapabilities.firefox();
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
			//	driverObject  = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote FIREFOX Driver...");
				break;

			case GOOGLECHROMEREMOTE:
				capabilities = DesiredCapabilities.chrome();
			//	driverObject  = new RemoteWebDriver(new URL("http://ec2-23-20-61-1.compute-1.amazonaws.com:4444/wd/hub"), capabilities);
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote Chrome remote Driver...");
				break;

			case INTERNETEXPLORERREMOTE:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				/*capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());*/
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
			//	driverObject  = new RemoteWebDriver(new URL("http://ec2-23-20-61-1.compute-1.amazonaws.com:4444/wd/hub"), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote Internet Explorer remote Driver...");
				break;
				
			case INTERNETEXPLORER10REMOTE:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				/*capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());*/
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
			//	driverObject  = new RemoteWebDriver(new URL("http://ec2-23-20-61-1.compute-1.amazonaws.com:4444/wd/hub"), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote Internet Explorer remote Driver...");
				break;
				
			case INTERNETEXPLORER9REMOTE:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				/*capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());*/
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
			//	driverObject  = new RemoteWebDriver(new URL("http://ec2-23-20-61-1.compute-1.amazonaws.com:4444/wd/hub"), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote Internet Explorer remote Driver...");
				break;
				
			case INTERNETEXPLORER8REMOTE:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				/*capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());*/
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
			//	driverObject  = new RemoteWebDriver(new URL("http://ec2-23-20-61-1.compute-1.amazonaws.com:4444/wd/hub"), capabilities);
				logger.debug(driverObject);
				logger.debug("Using Remote Internet Explorer remote Driver...");
				break;
			
			case SAFARIREMOTE:
				capabilities = DesiredCapabilities.safari();
				driverObject  = new RemoteWebDriver(new URL(System.getProperty("remoteHubLocation")), capabilities);
				capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
				logger.debug(driverObject);
				logger.debug("Using Remote Safari remote Driver...");
				break;

			case FIREFOX:
				
				
				capabilities=DesiredCapabilities.firefox();
				//DesiredCapabilities capabilities1 = DesiredCapabilities.firefox();
				//capabilities1.setCapability(capabilityName, value);
				
				capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
				capabilities.setJavascriptEnabled(true);
				
				String ffLocation = GlobalSettings.firefoxDriverLocation();
				System.setProperty("webdriver.gecko.driver", ffLocation);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
				driverObject = new FirefoxDriver();
				logger.debug("Using FIREFOX Driver...");
				break;
				
				
			case IE8:
			case IE9:
			case IE10:
				
				String ieExe = browserDetails.getIEDriverLocation();
				if(ieExe == null)
					ieExe = GlobalSettings.getIeDriverLocation();
				System.setProperty("webdriver.internetexplorer.driver", ieExe);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				ieservice = new InternetExplorerDriverService.Builder()
				.usingDriverExecutable(
						new File(ieExe))
						.usingAnyFreePort().build();
				driverObject = new InternetExplorerDriver(ieservice, capabilities);
				//driverObject = new InternetExplorerDriver();
				logger.debug("Using INTERNET EXPLORER Driver...");
				break;
				
				
			case GOOGLECHROME:
								
				String chromeExe = browserDetails.getChromeDriverLocation();
				if(chromeExe == null)
					chromeExe = GlobalSettings.getChromeDriverLocation();
				System.setProperty("webdriver.chrome.driver",chromeExe );
				/*service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File(chromeExe))
						.usingAnyFreePort().build();
				service.start();*/
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-extensions");
				options.addArguments("--test-type");
				options.addArguments("--ignore-certificate-errors");
				
				driverObject= new ChromeDriver(options);
				
				//driverObject = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
				
				logger.debug("Using GOOGLECHROME Driver...");
				break;
				
				
			case SAFARI:
				if(isSupportedPlatform()) { 
					driverObject = new SafariDriver();
					logger.debug("Using Safari Driver...");
				}
				else
					logger.debug("Current Platform is not supported - Safari Driver...");
			default:
				break;
			}

		} catch (Exception x) {
			logger.error("Error in setBrowser:  "+ x.getMessage());
			return driverObject;
		}
		return driverObject;
	}
	

	
	/**
	 * To verify the platform supported 
	 *
	 * @return Platform support status
	 */
	private static boolean isSupportedPlatform() {
	    Platform current = Platform.getCurrent();
	    return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
	  }

	  

	/**
	 * Configure the FireFox profile if using FireFox Driver
	 *
	 * @return FirefoxProfile
	 */
	/*private FirefoxProfile generateFirefoxProfile() {
		FirefoxProfile prf = new FirefoxProfile();
		prf.setPreference("dom.max_chrome_script_run_time", 60);
		prf.setPreference("setTimeoutInSeconds", 60);
		prf.setPreference("dom.max_script_run_time", 60);
		prf.setPreference("dom.popup_maximum", 0);
		prf.setPreference("privacy.popups.disable_from_plugins", 3);
		prf.setPreference("browser.xul.error_pages.enabled", false);
		prf.setPreference("general.useragent.extra.firefox", "Firefox");
		prf.setAcceptUntrustedCertificates(true);
		return (prf);
	}
*/
	/**
	 * Shut down any browser instances still open now that tests have finished
	 *
	 * @param driverObject - driver object to stop
	 */
	public void stopSelenium(WebDriver driverObject) {
		try {
			if (driverObject != null) {
				try {
					driverObject.quit();
					
				} catch (Exception x) {
					logger.error("Did not manage to quit driver object cleanly: {}"+ x.getMessage());
				}
				if(browserDetails.getBrowser().equals(BasicConfig.selectedBrowser.IE10)){
					Runtime.getRuntime().exec("taskkill /F /IM conhost.exe");
					Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
					Runtime.getRuntime().exec("taskkill /F /IM iedriver.exe");
				}
				else if(browserDetails.getBrowser().equals(BasicConfig.selectedBrowser.GOOGLECHROME))
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				else if(browserDetails.getBrowser().equals(BasicConfig.selectedBrowser.SAFARI))
					Runtime.getRuntime().exec("taskkill /F /IM Safari.exe");
				driverObject = null;
				
			}
		} catch (Exception x) {
			logger.error("Error Quitting Browser: "+ x.getMessage());
			logger.error("Killing Selenium!");
			Runtime.getRuntime().halt(1);
		}
	}


}