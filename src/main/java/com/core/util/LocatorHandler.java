package com.core.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Name : LocatorHandler
 * 
 * Description : Utility class which consists of the all the locator handler elements used by selenium
 * 
 
 * 
 **/

public class LocatorHandler {

	public static final Logger logger = LogManager.getLogger(LocatorHandler.class);
	
	public enum locatorType {

		CLASS_NAME, CSS_SELECTOR, XPATH, ID, LINK_TEXT, NAME, PARTIAL_LINK_TEXT, TAG_NAME, DOM
	}

	/**
     * Work out the locator type based upon the start of the locator text
     *
     * @param locator - Locator to scan to work out what sort of locator it is
     * @return locatorType - The type of locator
     */
	public final locatorType getLocatorType(String locator) {
		if (locator.startsWith("xpath")) {
			return locatorType.XPATH;
		} else if (locator.startsWith("dom")) {
			return locatorType.DOM;
		} else if (locator.startsWith("css")) {
			return locatorType.CSS_SELECTOR;
		} else if (locator.startsWith("name")) {
			return locatorType.NAME;
		} else if (locator.startsWith("class")) {
			return locatorType.CLASS_NAME;
		} else if (locator.startsWith("tag")) {
			return locatorType.TAG_NAME;
		} else if (locator.startsWith("link")) {
			return locatorType.LINK_TEXT;
		} else if (locator.startsWith("id")){
			return locatorType.ID;
		}
		return null;
	}

	 /**
     * Take a locator and work out what sort of By.xxx should be used
     *
     * @param locator - locator to evaluate
     * @return By - a By format that can utilize to get web element
     */
	public By autoLocator(String locator) {
		switch (getLocatorType(locator)) {
		case XPATH:
			if (locator.startsWith("xpath")) {
				locator = locator.split(",", 2)[1];
			}
			return By.xpath(locator);
		case CSS_SELECTOR:
			if (locator.startsWith("css")) {
				locator = locator.split(",", 2)[1];
			}
			return By.cssSelector(locator);
		case ID:
			if (locator.startsWith("id")) {
				locator = locator.split(",", 2)[1];
			}
			return By.id(locator);
		case NAME:
			if (locator.startsWith("name")) {
				locator = locator.split(",", 2)[1];
			}
			return By.name(locator);
		case CLASS_NAME:
			if (locator.startsWith("class")) {
				locator = locator.split(",", 2)[1];
			}
			return By.className(locator);
		case TAG_NAME:
			if (locator.startsWith("tag")) {
				locator = locator.split(",", 2)[1];
			}
			return By.tagName(locator);
		case LINK_TEXT:
			if (locator.startsWith("link")) {
				locator = locator.split(",", 2)[1];
			}
			return By.linkText(locator);
		default:
			if (locator.startsWith("dom:name")) {
				locator = locator.split(",", 2)[1];
				return By.xpath("//form[@name='" + locator + "']");
			} else if (locator.startsWith("dom:index")) {
				locator = locator.split(",", 2)[1];
				return By.xpath("(//form)[" + locator + "]");
			}
		}
		return null;
	}
}