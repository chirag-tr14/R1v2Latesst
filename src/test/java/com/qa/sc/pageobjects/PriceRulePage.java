package com.qa.sc.pageobjects;


import static com.r1v2.common.GlobalStaticInfo.*;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.core.util.Utility;
import com.r1v2.common.PageFactory;

public class PriceRulePage extends SCLoginPage {

	public PriceRulePage(WebDriver webDriver, PageFactory pgFactory) {
		super(webDriver, pgFactory);

	}

	private String drop_down;

	public boolean navigatePriceRulePage() {
		clickElement(INVTMGMT_INVTMANAGE_MENU);
		clickElement(INVTMGMT_PRICERULE_MENU);
		clickElement(INVTMGMT_PRICERULE_ADD_BTN);
		return true;
	}

	public boolean selectPriceCategoryDropdownitem(String option) {
		boolean flag = false;
		clickElement(INVTMGMT_PRICERULE_PRICECATEGORY_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_PRICECATEGORY_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean selectVehicleTypeDropdownitem(String option) {
		boolean flag = false;
		clickElement(INVTMGMT_RICERULE_VEHICLE_TYPE_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_VEHICLE_TYPE_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean selectProviderDropdownitem(String option){
		boolean flag = false;
		clickElement(INVTMGM_PRICERULE_PROVIDER_TYPE_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_PROVIDER_TYPE_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean startDate() {
		String format="dd/MM/yyyy";
			enterValue(INVTMGMT_PRICERULE_STARTDATE_TEXTBOX, Utility.getTodaysDate(format));
		return true;
	}
	
	public boolean selectDiscountDropdownitem(String option){
		boolean flag = false;
		clickElement(INVTMGMT_PRICERULE_DISCOUNT_TYPE_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_DISCOUNT_TYPE_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
		
	}
	public boolean selectMakeDropdownitem(String option) {
		boolean flag = false;
		clickElement(INVTMGMT_PRICERULE_MAKE_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_MAKE_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	public boolean selectModelDropdownitem(String option) {
		boolean flag = false;
		//clickElement(INVTMGMT_PRICERULE_MODEL_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_MODEL_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	public boolean selectTrimDropdownitem(String option) {
		boolean flag = false;
		//clickElement(INVTMGMT_PRICERULE_TRIM_LIST);

		List<WebElement> elements = returnWebElements(INVTMGMT_PRICERULE_TRIM_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.drop_down = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	
	public boolean discountValue(String value) {
				enterValue(INVTMGMT_PRICERULE_DISCOUNT_VALUE,value);
		return true;
	}
	
	public boolean discountSave(String value) {
		enterValue(INVTMGMT_PRICERULE_DISCOUNT_VALUE,value);
		return true;
   }
	
	public boolean savePriceRule(){
		 clickElement(INVTMGMT_PRICERULE_SAVE_BTN);
		 waitforPageTolaod(10);
		 //System.out.println(getWebDriver().getTitle());
		 return true;
	}

	
	
	
}
