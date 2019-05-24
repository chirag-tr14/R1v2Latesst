package com.r1v2.common;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.core.actionhandlers.InputActionsHandler;
import com.core.actionhandlers.WaitForActionsHandler;
import com.core.actions.InputActions;
import com.core.actions.WaitForActions;
import com.core.config.PropLocatorConfig;
import com.core.exceptions.FrameNotFoundException;
import com.core.reports.ExtentReport;
/*
import com.core.actionhandlers.WaitForActionsHandler;
import com.core.actions.WaitForActions;
import com.core.config.PropLocatorConfig;
import com.core.exceptions.HomepageNotLoadedException;*/
import com.core.reports.TestNGCustomReporter;
import com.core.util.LocatorHandler;


public class PageBase    {
    
	protected PageFactory pageFactory;
	protected WebDriver driver;
	public static Connection conn;
	private HashMap<String, String> objectLocMapping=new HashMap<String, String>();
	private LocatorHandler loc = new LocatorHandler();
	ExtentReport rt;
	
	
	public PageBase(WebDriver driver,PageFactory pageFactory) {
		this.driver=driver;
		this.pageFactory=pageFactory;
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public HashMap<String, String> getWebElementMapping() {
		if(objectLocMapping.size()<=0){
			setWebElementMapping();
		}
		return objectLocMapping;
	}
	

	public void setWebElementMapping() {
		try {
			objectLocMapping = new PropLocatorConfig()
			.getWebElementMapping();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	public WebElement returnWebElement(String key) {
		return genericGetWebElement(null, key, null , true).get(0);
	}

	public WebElement returnWebElement(String key,boolean report) {
		return genericGetWebElement(null, key, null , report).get(0);
	}

	public WebElement returnWebElement(WebElement parent,String key) {
		return genericGetWebElement(null, key, parent , true).get(0);
	}

	public WebElement returnWebElement(WebElement parent,String key,boolean report) {
		return genericGetWebElement(null, key, parent , report).get(0);
	}

	public List<WebElement> returnWebElements(String key) {
		return genericGetWebElement(null, key, null , true);
	}

	public List<WebElement> returnWebElements(WebElement parent,String key) {
		return genericGetWebElement(null, key, parent , true);
	}

	public List<WebElement> returnWebElements(String key, boolean report) {
		return genericGetWebElement(null, key, null , report);
	}

	public List<WebElement> returnWebElements(WebElement parent, String key, boolean report) {
		return genericGetWebElement(null, key, parent , report);
	}

	public WebElement returnElementByDynamicLocator(By locator) {
		return genericGetWebElement(locator, null, null , true).get(0);
	}

	public WebElement returnElementByDynamicLocator(By locator, WebElement parent ) {
		return genericGetWebElement(locator, null,  parent , true).get(0);
	}

	public WebElement returnElementByDynamicLocator(By locator, boolean report ) {
		return genericGetWebElement(locator, null,  null , true).get(0);
	}

	public WebElement returnElementByDynamicLocator(By locator, WebElement parent , boolean report) {
		return genericGetWebElement(locator, null,  parent , report).get(0);
	}

	public List<WebElement> returnElementsByDynamicLocator(By locator) {
		return genericGetWebElement(locator, null,  null , true);
	}

	public List<WebElement> returnElementsByDynamicLocator(By locator, WebElement parent ) {
		return genericGetWebElement(locator, null,  parent , true);
	}

	public List<WebElement> returnElementsByDynamicLocator(By locator, boolean report ) {
		return genericGetWebElement(locator, null,  null , report);
	}

	public List<WebElement> returnElementsByDynamicLocator(By locator, WebElement parent , boolean report) {
		return genericGetWebElement(locator, null,  parent , report);
	}

	private List<WebElement> genericGetWebElement(By locator, String key, WebElement parent , boolean report) {

		if (locator == null)
			locator = loc.autoLocator(getWebElementMapping().get(key));
		WaitForActions wait = new WaitForActionsHandler(driver);
		wait.untilWebElement(locator).exists();
		List<WebElement> elements = new ArrayList<WebElement>();
		try{
			if(parent == null)
				elements = driver.findElements(locator);
			
			else
				elements = parent.findElements(locator);
		}catch(StaleElementReferenceException e){
			wait.untilWebElement(locator).waitForStaleElement();
		}
		if (elements.size() > 0 && report) {
			if(key!=null){
				String[] rptr = key.split(Pattern.quote("."));
				TestNGCustomReporter.logbr(rptr+" is present");
				
				
			}
		}else if(elements.size() < 1){
			if(key==null){
				//TestNGCustomReporter.logbr("Element is not present for dynamic locator ");
				throw new NoSuchElementException("Element is not present for dynamic locator ");
			}else{
				String[] rptr = key.split(Pattern.quote("."));
				String elementName = Character.toUpperCase(rptr[1].charAt(0))+rptr[1].replace("_", " ").substring(1)+" "+rptr[2];
				TestNGCustomReporter.logbr(elementName+" is not present");
				String[] attribute = getWebElementMapping().get(key).split(Pattern.quote(","));
				String attributeName = attribute[0];
				String attributeValue =  attribute[1];
				TestNGCustomReporter.logbr("Element not found Exception occured. \n Element Name : " +elementName+"\n Attribute Name : "+attributeName+"\nAttribute Value :"+attributeValue);
			}
		}
		return elements;
		
	}


	
	
	public void openHomepage(String homepageURL)
	{
		getWebDriver().get(homepageURL);
		TestNGCustomReporter.logbr("Opened URL : "+homepageURL);
		
	}

	
	public  String openFrontEndPage(String frontendUrl)
	{
		getWebDriver().get(frontendUrl);
		TestNGCustomReporter.logbr("Opened URL : "+frontendUrl);
	    return frontendUrl;
	}
	

	public boolean alertAccept(){
			getWebDriver().switchTo().alert().accept();
		return true;
	}
	
	
	public boolean getCurrentUrl() {
		getWebDriver().getCurrentUrl();
		return true;
	}
	
	
	
	
	public String getText(String key) {
		return getText(returnWebElement(key));
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(WebElement parent, String key) {
		return getText(returnWebElement(parent , key));
	}

	public void clickElement(WebElement element) {
		TestNGCustomReporter.logbr("Clicked on "+element.getText());
			element.click();
	}
	
	public void clickElements(WebElement element, String Value) {
		TestNGCustomReporter.logbr("Clicked on "+element.getText());
			element.click();
	}

	
	public void clickElement(String key) {
		clickElement(returnWebElement(key));
	}

	
	
	public void replaceValueClikElement(String key,String placeholderValue, String replaceValue) {
		String value = getWebElementMapping().get(key);
		value = value.replace(placeholderValue, replaceValue);
		getWebElementMapping().put(key,value);
		clickElement(returnWebElement(key));
	}
	
	
	
	/*public void replaceValueVerifyElement(String key,String placeholderValue, String replaceValue) {
		String value = getWebElementMapping().get(key);
		value = value.replace(placeholderValue, replaceValue);
		getWebElementMapping().put(key,value);
		verifyWebElement(returnWebElement(key));
	}*/
	
	
	
	public void clickElement(WebElement root, String key) {
		clickElement(returnWebElement(root, key));
	}

	public void enterValue(WebElement element,String value) {
		element.click();
		element.clear();
		if(element.getTagName().equals(GlobalStaticInfo.TEXTBOX))
			element.clear();
		element.sendKeys(value);
		TestNGCustomReporter.logbr("Entered value "+value);
		
	}
	
	public void enterValue(String key,String value) {
		enterValue(returnWebElement(key), value);
	}


	public boolean verifyWebElement(String key) {
		return verifyWebElement(returnWebElement(key));
	}

	public boolean verifyWebElement(WebElement element) {
		if(element.isDisplayed())
		TestNGCustomReporter.logbr("Element Displayed "+ element.getText());
	
		else
		TestNGCustomReporter.logbr("Element Not Displayed "+ element.getText());
		
		return element.isDisplayed();
	}

	public boolean verifyWebElement(WebElement parent, String key) {
		return verifyWebElement(returnWebElement(parent,key));
	}

	public String getCssValue(String key,String cssProperty) {
		WebElement element=returnWebElement(key);
		return element.getCssValue(cssProperty);
	}

	public String getCssValue(WebElement element,String cssProperty) {
		return element.getCssValue(cssProperty);
	}

	public boolean verifyElementEnabled(WebElement element) {
		if(element.isEnabled())
			TestNGCustomReporter.logbr("Element Enabled "+ element.getText());
		else
		TestNGCustomReporter.logbr("Element Not Enabled "+ element.getText());
		return element.isEnabled();
	}

	public boolean verifyElementEnabled(String key) {
		return verifyElementEnabled(returnWebElement(key));
	}

	public boolean verifyElementSelected(String key) {
		WebElement element = returnWebElement(key);
		if(element.isSelected())
		TestNGCustomReporter.logbr("Element is selected "+ element.getText());
				else
		TestNGCustomReporter.logbr("Element is not selected "+ element.getText());
			return element.isSelected();
	}

	public void refreshPage(){
		getWebDriver().navigate().refresh();
		TestNGCustomReporter.logbr("Page Refreshed");
	}

	public void closeCurrentBrowser(){
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "window.onbeforeunload = null;" + "window.close();";
		js.executeScript(script);*/
		driver.close();
		
	}

	public void handleIEError(){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyExpectedText(String key,String expected) {
		String temp=getText(key);
		boolean flag=temp.trim().equalsIgnoreCase(expected.trim());
		if(flag)
		TestNGCustomReporter.logbr(expected+" is displayed");
		else
		TestNGCustomReporter.logbr(expected+" is not displayed");
		return flag;
	}

	
	
	
	public void waitforPageTolaod(int time){
		int t=time*1000;
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public boolean verifyDropDown(String Key)
	{
		return returnWebElement(Key).getTagName().equals(GlobalStaticInfo.DROPDOWN);
	}
	
	
	public boolean switchToFrame(String id){
		try{
		    getWebDriver().switchTo().frame(id);
		   			return true;
		}catch(NoSuchFrameException e){
			try{
				List<WebElement> iframes = getWebDriver().findElements(By.tagName("iframe"));
				if (!iframes.isEmpty()) {
					for (WebElement iframe : iframes) {
						if (iframe.getAttribute("id").equals(id)) {
							getWebDriver().switchTo().frame(id);
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
	

	
	
	
	
	
	
	
	
/*	public void  MouseHover(String key){
		InputActionsHandler action= new InputActionsHandler(driver);
		action.getMouseObject().click(where);
		Actions builder = new Actions(driver);  

		WebElement Element = driver.findElement(By.xpath("//div[@id='signUpModal']")); 

		builder.moveToElement(Element, 100, 100).click().build().perform();

	}*/
}


