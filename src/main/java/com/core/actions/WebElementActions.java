package com.core.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
 * Name : WebElementActions
 * 
 * Description : An interface to be implemented when you are handling Elements of a WebPage
 * 

 * 
 * Version:1.0
 */
public interface WebElementActions {


	/**
     * Find out if an element is stale or not.
     *
     * @param element - webelemnt
     * @return boolean  - True if element is stale, otherwise false.
     */
    public boolean isElementStale(WebElement element) ;

	/**
     * Find out if an element exists or not.
     *
     * @param locator - A By locator
     * @return boolean  - True if element is found, otherwise false.
     */
	public boolean doesElementExist(By locator) ;

	 /**
     * Function to enable us to find out if an element is visible or not.
     *
     * @param locator - A By locator
     * @return boolean  - True if element is displayed, otherwise false.
     */
	public boolean isElementDisplayed(By locator) ;
	
	/**
     * Gets the element count for a given locator
     *
     * @param locator - Locator to use for count
     * @return int - Number of instances of locator
     */
	public int getElementCount(String locator);
	
	 /**
     * Check a checkbox
     *
     * @param locator - Locator of checkbox that you want to check
     */
    public void check(String locator) ;

    /**
     * Uncheck a checkbox
     *
     * @param locator - Locator of checkbox that you want to uncheck
     */
    public void uncheck(String locator) ;

    /**
     * Check to see if a checkbox is checked or not
     *
     * @param locator - Locator of checkbox that you want to check
     * @return boolean True or false
     */
	public boolean isChecked(String locator);
}
