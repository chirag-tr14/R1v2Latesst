package com.core.actionhandlers;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

import com.core.actions.WindowActions;
import com.core.exceptions.FrameNotFoundException;
import com.core.exceptions.TooFewWindowsException;
import com.core.exceptions.TooManyWindowsException;
import com.core.exceptions.UnableToFindWindowException;
import com.core.exceptions.WindowNotFoundException;

/**
 * Name : WindowActionsHandler
 * 
 * Description : This class is an implementer for WindowActions.
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
public class WindowActionsHandler implements WindowActions{

	public static final Logger logger = LogManager.getLogger(WindowActionsHandler.class);


	private WebDriver driver;
	private String parentHandle;
	private String handle;

	//private String name;
	//private static int instanceCount = 0;

	public WindowActionsHandler(WebDriver parent, String url, String child) {

		this.driver = parent;
		parentHandle = parent.getWindowHandle();
		switchToWindow().get(url);
		handle = child;
		//name = createUniqueName();
		//handle = createWindow(url);

	}

	public WindowActionsHandler(WebDriver driver){
		this.driver=driver;
	}

	public String getWindowHandle(){
		return handle;
	}

	public String getParentHandle(){
		return parentHandle;
	}

	public void close(){
		switchToWindow().close();
		handle = "";
		//Switch back to the parent window
		driver.switchTo().window(parentHandle);
	}

	public WebDriver switchToWindow(){
		checkForClosed();
		return driver.switchTo().window(handle);
	}

	public WebDriver switchToParent(){
		checkForClosed();
		return driver.switchTo().window(parentHandle);
	}

	private void checkForClosed(){
		if ( handle  == null || handle.equals("") )
			throw new WebDriverException("Web Window closed or not initialized");
	}


	/*private static String createUniqueName() {
		return "a_Web_Window_" + instanceCount++;
	}
	private String createWindow(String url){

		//Record old handles
		Set<String> oldHandles = driver.getWindowHandles();
		parentHandle = driver.getWindowHandle();

		//Inject an anchor element
		((JavascriptExecutor) driver).
		executeScript(
				injectAnchorTag(name,url)
				);

		//Click on the anchor element
		driver.findElement(By.id(name)).click();

		handle = getNewHandle(oldHandles);

		return  handle;
	}

	private String getNewHandle(Set<String> oldHandles){

		Set<String> newHandles = driver.getWindowHandles();
		newHandles.removeAll(oldHandles);

		//Find the new window
		for(String handle : newHandles )
			return handle;


		return null;
	}

	private String injectAnchorTag(String id, String url){
		return String.format(  "var anchorTag = document.createElement('a'); " +
				"anchorTag.appendChild(document.createTextNode('nwh'));" +
				"anchorTag.setAttribute('id', '%s');" +
				"anchorTag.setAttribute('href', '%s');" +
				"anchorTag.setAttribute('target', '_blank');" +
				"anchorTag.setAttribute('style', 'display:block;');" +
				"document.getElementsByTagName('body')[0].appendChild(anchorTag);",
				id, url
				);
	}*/

	public Boolean switchBetweenWindows(){
		Set<String> listOfWindows = driver.getWindowHandles();
		if (listOfWindows.size() != 2) {
			if (listOfWindows.size() > 2) {
				throw new TooManyWindowsException();
			} else {
				throw new TooFewWindowsException();
			}
		}
		String currentWindow = driver.getWindowHandle();
		for (Iterator<String> i = listOfWindows.iterator(); i.hasNext(); ) {
			String selectedWindowHandle = i.next().toString();
			if (!selectedWindowHandle.equals(currentWindow)) {
				driver.switchTo().window(selectedWindowHandle);
				return true;
			}
		}
		throw new UnableToFindWindowException("Unable to switch windows!");
	}

	public Boolean switchToWindowTitled(String windowTitle){
		try{
			driver.switchTo().window(windowTitle);
			return true;

		}catch (Exception E) {
			try {
				Set<String> availableWindows = driver.getWindowHandles();
				if (!availableWindows.isEmpty()) {
					for (String windowId : availableWindows) {
						if (driver.switchTo().window(windowId).getTitle().contains(windowTitle)) {
							return true;
						} 
					}
				}
				return false;   

			} catch (Exception Ex) {
				throw new WindowNotFoundException("Unable to find a window with the title '" + windowTitle + "'!");
			}
		}
	}

	public Boolean switchToWindowTitleContains(String windowTitle){
		try {
			driver.switchTo().window(windowTitle);
			return true;
		}
		catch (Exception Ex) {

			try {
				Set<String> availableWindows = driver.getWindowHandles();
				if (!availableWindows.isEmpty()) {
					for (String windowId : availableWindows) {
						if (driver.switchTo().window(windowId).getTitle().toLowerCase().contains(windowTitle.toLowerCase())) {
							return true;
						} 
					}
				}
				return false;   

			} catch (Exception E) {
				throw new WindowNotFoundException("Unable to find a window with the title '" + windowTitle + "'!");
			}
		}
	}

	public File takeScreenshot() {
		WebDriver augment = new Augmenter().augment(driver);
		return ((TakesScreenshot) augment).getScreenshotAs(OutputType.FILE);
	}

	public void maximize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), (int) toolkit.getScreenSize().getHeight());
		driver.manage().window().setSize(screenResolution);
	}

	public boolean switchToFrame(String id){
		try{
			driver.switchTo().frame(id);
			return true;
		}catch(NoSuchFrameException e){
			try{
				List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
				if (!iframes.isEmpty()) {
					for (WebElement iframe : iframes) {
						if (iframe.getAttribute("id").equals(id)) {
							driver.switchTo().frame(id);
							return true;
						}
					}
				}
				return false;   
			} catch (Exception Ex) {
				throw new FrameNotFoundException("Unable to find a frame with the id '" + id + "'!");
			}
		}
	}

	public void switchToNewTab(String oldTab) {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));

	}


}
