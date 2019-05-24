package com.core.actionhandlers;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.core.actions.WebElementActions;
import com.core.exceptions.InvalidElementTypeException;
import com.core.util.LocatorHandler;

/**
 * Name : WebElementActionsHandler
 * 
 * Description : This class is an implementer for WebElementActions.
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
public  class WebElementActionHandler implements WebElementActions{


	private WebDriver driver;
	private LocatorHandler loc = new LocatorHandler();
	public static final Logger logger = LogManager.getLogger(WebElementActionHandler.class);
	
	public WebElementActionHandler(WebDriver driver){
		this.driver=driver;
	}
	
	public boolean isElementStale(WebElement element) {
		try {
			element.getLocation();
		} catch (StaleElementReferenceException Ex) {
			return true;
		}
		return false;
	}

	public boolean doesElementExist(By locator) {
		if (driver.findElements(locator).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementDisplayed(By locator) {
		if (doesElementExist(locator)) {
			return driver.findElement(locator).isDisplayed();
		} else {
			return false;
		}
	}

	public int getElementCount(String locator) {
		List<WebElement> elementsFound = driver.findElements(loc.autoLocator(locator));
		return elementsFound.size();
	}

	

	public void check(String locator) {
		WebElement checkBox = driver.findElement(loc.autoLocator(locator));
		if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
			throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
		}
		if (!checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public void uncheck(String locator) {
		WebElement checkBox = driver.findElement(loc.autoLocator(locator));
		if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
			throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
		}
		if (checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public boolean isChecked(String locator) {
		WebElement checkBox = driver.findElement(loc.autoLocator(locator));
		if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
			throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
		}
		if (checkBox.getAttribute("checked").equals("checked")) {
			return true;
		} else {
			return false;
		}
	}
}
