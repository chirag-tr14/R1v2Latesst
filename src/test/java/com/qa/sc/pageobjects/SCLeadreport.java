package com.qa.sc.pageobjects;

import static com.r1v2.common.GlobalStaticInfo.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.core.reports.TestNGCustomReporter;
import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.LocatorHandler;
import com.core.util.PropertyFileUtil;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;
import com.r1v2.common.PageBase;
import com.r1v2.common.PageFactory;

import org.openqa.selenium.support.ui.Select;

public class SCLeadreport extends SCLoginPage {
	
	BaseTest t= new BaseTest();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
	private Map<String, String> td = t.getTestDataProperties();
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	
	CSVTable leaddetailsreport = new CSVTable(td.get(propUtil.getString("region")+".leadDetailsReportFilepath"));
	List<CSVTableRow> page = leaddetailsreport.getRecords();
	CSVTableRow pagesdata = page.get(0);
	
	public String id =pagesdata.getString("ID");
	//public int i=199;
	private LocatorHandler loc = new LocatorHandler();
	
	public SCLeadreport(WebDriver webDriver, PageFactory pgFactory) {
		super(webDriver, pgFactory);
		
	}
	
public SCLeadreport selectOrganization(){
		clickElement(HOMEPAGE_SELECT_ORGANIZATION);
			return this;
	}
	
	public boolean selectSiteList(String dealer){
		clickElement(PAGEBUILDER_SITELIST_MENU);
			enterValue(PAGEBUILDER_SITELIST_SEARCH, dealer);
		clickElement(PAGEBUILDER_SITELIST_DLRSELECT);
		
	return true;
}
	

	public boolean navigateLeadDetailsReport(){
		clickElement(LEADDETAILS_REPORTS_MENU);
		clickElement(LEADDETAILS_LEADDETAILS_MENU);
	    
		return true;
	}
	
	public boolean selectNewLeads(){
	clickElement(LEADDETAILS_LEADDETAILS_LIST);
	clickElement(LEADDETAILS_NEWLEADS);
	clickElement(LEADDETAILS_SUBMIT);
	    
		return true;
	}
	
	public boolean selectprocessedLeads(){
	clickElement(LEADDETAILS_LEADDETAILS_LIST);
	clickElement(LEADDETAILS_PROCESSEDLEADS);
	clickElement(LEADDETAILS_SUBMIT);
	    
		return true;
	}
	
	public boolean selectjunkleads(){
		clickElement(LEADDETAILS_LEADDETAILS_LIST);
		clickElement(LEADDETAILS_JUNKLEADS);
		clickElement(LEADDETAILS_SUBMIT);
		return true;
		}
	
	public boolean selectdeletedleads(){
		
		clickElement(LEADDETAILS_LEADDETAILS_LIST);
		clickElement(LEADDETAILS_DELETEDLEADS);
		clickElement(LEADDETAILS_SUBMIT);
		    
			return true;
		}
	
	public boolean selectconfmissedlead(){
		clickElement(LEADDETAILS_LEADDETAILS_LIST);
		clickElement(LEADDETAILS_CONFMISSEDLEAD);
		clickElement(LEADDETAILS_SUBMIT);
		    
			return true;
		}
	
	public boolean selectshowall(){	
		
		clickElement(LEADDETAILS_LEADDETAILS_LIST);
		clickElement(LEADDETAILS_SHOWALL);
	    clickElement(LEADDETAILS_SUBMIT);
		return true;
		}
	
	public boolean selectOEM(String oem){
		
		//splitValue(locator);
		enterValue(PAGEBUILDER_OEMLIST_SEARCH, oem);
		clickElement(PAGEBUILDER_OEMLIST_OEMSELECT);
		 
		 //WebElement wd =driver.findElement(By.xpath("//a[contains(@onclick,'select1("+id+"')]"));
		 //wd.click();
		
		return true;

}
	
//	public By splitValue(String locator){
//		
//		By wb= loc.autoLocator(locator);
//		
//		
//		locator.replaceAll("+i+", id);
//		return wb;
//}
	
	public boolean selectGroup(String group){
		enterValue(PAGEBUILDER_GROUPLIST_SEARCH, group);
		clickElement(PAGEBUILDER_GROUPLIST_GROUPSELECT);
		return true;
}

	public boolean selectDealer(String dealer){
		clickElement(PAGEBUILDER_SITELIST_MENU);
			enterValue(PAGEBUILDER_SITELIST_SEARCH, dealer);
		clickElement(PAGEBUILDER_SITELIST_DLRSELECT);
		return true;
		
	}
	
	public  boolean dateselection(){
		clickElement(LEADDETAILS_FROM_BTN);
		clickElement(LEADDETAILS_DATEPICK);
		clickElement(LEADDETAILS_SUBMIT);
		selectshowall();
		return true;
        }
	
	public boolean movetodeleted(){
		selectshowall();
		driver.navigate().refresh();
		clickElement(LEADDETAILS_SELECTLEAD);
		clickElement(LEADDETAILS_MOVETODELETED);
		getWebDriver().switchTo().alert().accept();
				selectdeletedleads();
		//String expected=getText(LEADDETAILS_FORMNAME);
		//String actual=pagesdata.getString("Formname");
		//return verifyExpectedText(actual,expected);
		return true;
	}
	
	public boolean reprocess(){
		selectshowall();
		driver.navigate().refresh();
		clickElement(LEADDETAILS_SELECTLEAD);
		clickElement(LEADDETAILS_REPROCESSLEAD);
		driver.switchTo().alert().accept();
		return true;
	}
	
	public boolean movetojunk(){
		selectshowall();
		driver.navigate().refresh();
		clickElement(LEADDETAILS_SELECTLEAD);
		clickElement(LEADDETAILS_JUNKLEAD);
		driver.switchTo().alert().accept();
		return true;
	}
	
	public boolean numberofleads(){
		
		clickElement(LEADDETAILS_NUMBEROFLEADS);
		return true;
	}
	
	public boolean numberofleads1(){
		
		clickElement(LEADDETAILS_NUMBEROFLEADS1);
		return true;
	}
	
	public boolean numberofleads2(){
	
		clickElement(LEADDETAILS_NUMBEROFLEADS2);
		return true;
	}
	
	public boolean numberofleads3(){
	
		clickElement(LEADDETAILS_NUMBEROFLEADS3);
		return true;
	}
	
	
	
}