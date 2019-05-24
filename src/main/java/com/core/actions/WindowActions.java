package com.core.actions;

import java.io.File;

import org.openqa.selenium.WebDriver;
/**
 * Name : WindowActions
 * 
 * Description : This interface will be implemented while automating the windows
 * 
 * 
 *
 * Version : 1.0
 */
public interface WindowActions {

	
	/**
     * Switch to current/child window.
     *
     * @return WebDriver
     */
	public WebDriver switchToWindow();

	/**
     * Switch to parent window.
     *
     * @return WebDriver
     */
	public WebDriver switchToParent();
	
	/**
     * This function will switch to the next window if you currently have two windows.
     * If you have more or less than two windows this will return a false.
     *
     * @return Boolean - True if successful, otherwise false
     */
	public Boolean switchBetweenWindows();

	/**
     * Shortcut for driver.switchTo().window(windowTitle) which will return a boolean you can assert/verify on
     *
     * @param windowTitle - Title of window you want to try and switch to
     * @return Boolean - True if successful, otherwise false
     */
	public Boolean switchToWindowTitled(String windowTitle);

	/**
     * Returns the current window handle.
     *
     * @return String
     */
	
	public Boolean switchToWindowTitleContains(String windowTitle);
	public String getWindowHandle();

	/**
     * Returns the parent window handle.
     *
     * @return String
     */
	public String getParentHandle();

	
	/**
     * Close the current window and switched back to parent window.
     */
	public void close();

	/**
     * Take a screenshot of the current window.
     *
     * @return File
     */
	public File takeScreenshot() ;

	/**
     * Find out the resolution of the current window and set the browser window size to be the same.
     */
	public void maximize() ;
	
	/**
     * Switch to frame in a page
     *
     * @param id - frame id 
	 * @return 
     */
	public boolean switchToFrame(String id);
	
	/**
     * Switch to new tab opened
     *
     */
	public void switchToNewTab(String oldTab);

}
