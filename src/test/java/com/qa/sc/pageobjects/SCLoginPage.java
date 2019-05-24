package com.qa.sc.pageobjects;

import static com.r1v2.common.GlobalStaticInfo.HOMEPAGE_LOGOUT_BUTTON;
import static com.r1v2.common.GlobalStaticInfo.LOGINPAGE_LOGIN_LOGIN_BUTTON;
import static com.r1v2.common.GlobalStaticInfo.LOGINPAGE_LOGIN_LOGIN_HELPTEXT;
import static com.r1v2.common.GlobalStaticInfo.LOGINPAGE_LOGIN_PASSWORD;
import static com.r1v2.common.GlobalStaticInfo.LOGINPAGE_LOGIN_USERNAME;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import com.core.config.BasicConfig.selectSite;
import com.core.maindriver.DriverScript;
import com.r1v2.common.PageBase;
import com.r1v2.common.PageFactory;

public class SCLoginPage extends PageBase {
	private String scURL;
	
	
	
	public SCLoginPage(WebDriver webDriver, PageFactory pgFactory) {
		super(webDriver, pgFactory);
	}

	public SCLoginPage openSCLoginpage() {
		scURL = DriverScript.settings.getHomePage(selectSite.admin_home);
		openHomepage(scURL);
		return this;
	}
	
	public   SCPages frontendUrl(String url){
		
		openHomepage(url);
		return getPageFactory().scHomePage();
		
	}
	
		
	public SCLoginPage openBrowser() {
		DriverScript.settings.getBrowser();
			return this;
	}
	
	public boolean veirfyPageTitle(String title) {
		boolean flag=getWebDriver().getTitle().equalsIgnoreCase(title);
		return flag;
	}
	
	public SCLoginPage enterEmail(String email) {
		enterValue(LOGINPAGE_LOGIN_USERNAME, email);
		return this;
	}

	public SCLoginPage enterPassword(String password) {
		enterValue(LOGINPAGE_LOGIN_PASSWORD, password);
		return this;
	}
	
	public SCLoginPage clickOnLoginButton(){
		clickElement(LOGINPAGE_LOGIN_LOGIN_BUTTON);
		return this;
	}
		
	public SCLoginPage accetpAlert(){
		alertAccept();
		return this;
	}
		
	public boolean VerifyhelpTextClikable(){
		boolean flag=false;
		clickElement(LOGINPAGE_LOGIN_LOGIN_HELPTEXT);
		String Helptext=getWebDriver().getTitle();
		if (Helptext.equalsIgnoreCase(Helptext)) {
			flag= true;			
			} 
		else {
			flag=false;
		}
		return flag;
	}
	
	public SCPages goToHomePage(String email,String password){
		enterEmail(email);
		enterPassword(password);
				clickOnLoginButton();
		return getPageFactory().scHomePage();
	}
	
	public boolean logoutAdmin(){
		clickElement(HOMEPAGE_LOGOUT_BUTTON);
		 //closeCurrentBrowser();
		 return true;
	}
	
	public boolean browserClose(){
		closeCurrentBrowser();
		return true;
	}
}
	
	
	

