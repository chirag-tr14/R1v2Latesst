package com.qa.sc.pageobjects;

import static com.r1v2.common.GlobalStaticInfo.HOMEPAGE_CHANGE_PASSWORD;
import static com.r1v2.common.GlobalStaticInfo.HOMEPAGE_LOGOUT_BUTTON;
import static com.r1v2.common.GlobalStaticInfo.HOMEPAGE_SELECT_ORGANIZATION;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_CAMPAIGN_PAGEBTN;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMOM_OEM_AREA_CHECKBOX;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMOM_OEM_LABEL;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_ENGLISHTITLE;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_FORMCATEGORY_LABELS;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_MANDATORY_TEXT;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_RESPONSIVEBODY;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_RESPONSIVEFRAME1;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_RESPONSIVEFRAME2;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_SAVEBUTTON;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_TITLE;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_COMMON_URL;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_CONTENT_PAGERBTN;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_DEPARTMENT_DROPDOWN_LIST;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_ENGLISH_URL;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_FRONTEND_CONTENTPAGE;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_GROUPLIST_GROUPSELECT;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_GROUPLIST_SEARCH;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_OEMLIST_OEMSELECT;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_OEMLIST_SEARCH;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_PAGEBUILDER_ADD_BTN;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_PAGEBUILDER_MENU;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_SITEBUILDER_MENU;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_SITELIST_DLRSELECT;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_SITELIST_MENU;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_SITELIST_SEARCH;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_SPECIAL_PAGE;
import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER__FORMCATEGORY_DROPDOWN_LIST;
import static com.r1v2.common.GlobalStaticInfo.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.core.reports.TestNGCustomReporter;
import com.core.util.PropertyFileUtil;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;
import com.r1v2.common.PageFactory;

public class SCPages extends SCLoginPage {
	public SCPages(WebDriver webDriver, PageFactory pgFactory) {
		super(webDriver, pgFactory);
	}

	DataBase database = getPageFactory().databse();
	public String department_name;
	BaseTest t = new BaseTest();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
	private Map<String, String> td = t.getTestDataProperties();
	String regiondatabase = td.get(propUtil.getString("region") + ".env");

	public SCPages frontendUrl(String url) {
		openHomepage(url);
			return this;
	}

	public SCPages selectOrganization() {
		clickElement(HOMEPAGE_SELECT_ORGANIZATION);
		return this;
	}

	public boolean clickFirstTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_FIRSTTAB_NEXTBTN);
		return true;
	}
	
	public boolean clickFirstTabNextBtn1() {
		clickElement(PAGEBUILDR_COMMON_FIRSTTAB_NEXTBTN);
		waitforPageTolaod(1);
		alertAccept();
		return true;
	}
	public boolean clickSecondTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_SECONDTAB_NEXTBTN);
		return true;
	}

	public boolean clickThirdTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_THIRDTAB_NEXTBTN);
		waitforPageTolaod(1);
		return true;
	}
	public boolean clickFourthTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_FOURTHTAB_NEXTBTN);
		waitforPageTolaod(1);
		return true;
	}
	
	
	public boolean clickFifthTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_FIFTHTAB_NEXTBTN);
		waitforPageTolaod(1);
		return true;
	}
	
	public boolean clickSixthTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_SIXTHTAB_NEXTBTN);
		waitforPageTolaod(1);
		return true;
	}
	public boolean clickSeventhTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_SEVENTHTAB_NEXTBTN);
		return true;
	}
	public boolean clickEightTabNextBtn() {
		clickElement(PAGEBUILDR_COMMON_EIGHTHTAB_NEXTBTN);
		return true;
	}
	
	
	
	public boolean selectDealer(String dealer) {
		clickElement(PAGEBUILDER_SITELIST_MENU);
		enterValue(PAGEBUILDER_SITELIST_SEARCH, dealer);
		clickElement(PAGEBUILDER_SITELIST_DLRSELECT);
		return true;
	}

	public boolean searchGroup(String group) {
		enterValue(PAGEBUILDER_GROUPLIST_SEARCH, group);
		return true;
	}

	public boolean selectGroup(String placeholderValue, String replaceValue) {
		replaceValueClikElement(PAGEBUILDER_GROUPLIST_GROUPSELECT, placeholderValue, replaceValue);
		return true;

	}

	public boolean searchOEM(String oem) {
		enterValue(PAGEBUILDER_OEMLIST_SEARCH, oem);
		return true;
	}

	public boolean selectOEM(String placeholderValue, String replaceValue) {
		replaceValueClikElement(PAGEBUILDER_OEMLIST_OEMSELECT, placeholderValue, replaceValue);
		return true;
	}

	public boolean navigatePageBuilder() {
		clickElement(PAGEBUILDER_SITEBUILDER_MENU);
		waitforPageTolaod(1);
		clickElement(PAGEBUILDER_PAGEBUILDER_MENU);
		clickElement(PAGEBUILDER_PAGEBUILDER_ADD_BTN);
		return true;
	}

	public boolean navigateEditPageBuilder(String Url) {
		clickElement(PAGEBUILDER_SITEBUILDER_MENU);
		waitforPageTolaod(1);
		clickElement(PAGEBUILDER_PAGEBUILDER_MENU);
		enterValue(PAGEBUILDER_COMMON_SEARCHFIELD, Url);
		clickElement(PAGEBUILDER_COMMON_PAGE_EDIT_ICON);
		return true;
	}

	public boolean navigateDeletePageBuilder(String Url) {
		clickElement(PAGEBUILDER_SITEBUILDER_MENU);
		waitforPageTolaod(1);
		clickElement(PAGEBUILDER_PAGEBUILDER_MENU);
		enterValue(PAGEBUILDER_COMMON_SEARCHFIELD, Url);
		clickElement(PAGEBUILDER_COMMON_PAGE_DELETE_ICON);
		alertAccept();
		return true;
	}
	
	public boolean selectOneColumnLayout() {
		clickElement(PAGEBUILDER_COMMON_1COLUMN_RADIO_BTN);
		return true;
	}

	public boolean verifyOEMArea() {
		boolean flag1 = verifyWebElement(PAGEBUILDER_COMMOM_OEM_AREA_CHECKBOX);
		if (flag1) {
			TestNGCustomReporter.logbr("OEM Area CheckBox is displayed ");
		} else {
			TestNGCustomReporter.logbr("OEM Area CheckBox is not displayed");
		}

		boolean flag2 = verifyWebElement(PAGEBUILDER_COMMOM_OEM_LABEL);
		if (flag2) {
			TestNGCustomReporter.logbr("OEM area label is displayed");
		} else {
			TestNGCustomReporter.logbr("OEM area is not displayed");
		}
		return (flag1 && flag2);
	}

	public boolean selectOEMArea() {
		clickElement(PAGEBUILDER_COMMOM_OEM_AREA_CHECKBOX);
		return true;
	}

	public boolean pageTitle(String title) {
		enterValue(PAGEBUILDER_COMMON_TITLE, title);
		return true;
	}

	public boolean englishPageTitle(String title) {
		enterValue(PAGEBUILDER_COMMON_ENGLISHTITLE, title);
		return true;
	}

	public boolean verifyEditFrenchTitle(String placeholderValue, String replaceValue) {
		replaceValueClikElement(PAGEBUILDER_COMMON_FRENCHTITEL, placeholderValue, replaceValue);
		/// enterValue(PAGEBUILDER_COMMON_FRENCHTITEL, replaceValue);
		return true;
	}

	public boolean verifyEditFrenchTitle1(String placeholderValue, String replaceValue) {
		// replaceValueClikElement(PAGEBUILDER_COMMON_FRENCHTITEL,placeholderValue,replaceValue);
		enterValue(PAGEBUILDER_COMMON_FRENCHTITEL, replaceValue);
		return true;
	}

	public boolean pageUrl(String url) {
		enterValue(PAGEBUILDER_COMMON_URL, url);
		return true;
	}

	public boolean englishPageUrl(String url) {
		enterValue(PAGEBUILDER_ENGLISH_URL, url);
		return true;
	}

	public boolean verifyMandatoryFieldSecondTab() {
		boolean flag = verifyWebElement(PAGEBUILDR_COMMON_SECONDTAB_NEXTBTN);
		if (flag) {
			TestNGCustomReporter.logbr("Field is mandatory");
			clickElement(PAGEBUILDR_COMMON_SECONDTAB_NEXTBTN);
			TestNGCustomReporter.logbr("Alert Popup is Display");
			alertAccept();
		} else {
			TestNGCustomReporter.logbr("Field is not mandatory");
			clickElement(PAGEBUILDR_COMMON_SECONDTAB_NEXTBTN);
			TestNGCustomReporter.logbr("Alert Popup is  not Display");
		}
		return flag;
	}

	public boolean verifyMandatoryFieldThirdTab() {
		boolean flag = verifyWebElement(PAGEBUILDR_COMMON_THIRDTAB_NEXTBTN);
		if (flag) {
			TestNGCustomReporter.logbr("Field is mandatory");
			clickElement(PAGEBUILDR_COMMON_THIRDTAB_NEXTBTN);
			TestNGCustomReporter.logbr("Alert Popup is Display");
			alertAccept();
		} else {
			TestNGCustomReporter.logbr("Field is not mandatory");
			clickElement(PAGEBUILDR_COMMON_THIRDTAB_NEXTBTN);
			TestNGCustomReporter.logbr("Alert Popup is  not Display");
		}
		return flag;
	}

	public boolean verifyMandatoryField() {
		boolean flag = verifyWebElement(PAGEBUILDER_COMMON_MANDATORY_TEXT);
		if (flag) {
			TestNGCustomReporter.logbr("Field is mandatory");
			clickElement(PAGEBUILDER_COMMON_SAVEBUTTON);
			TestNGCustomReporter.logbr("Alert Popup is Display");
			alertAccept();
		} else {
			TestNGCustomReporter.logbr("Field is not mandatory");
			clickElement(PAGEBUILDER_COMMON_SAVEBUTTON);
			TestNGCustomReporter.logbr("Alert Popup is  not Display");
		}
		return flag;
	}

	public boolean verifyLogOutAndChangePasswordLinks() {
		boolean flag1 = verifyWebElement(HOMEPAGE_LOGOUT_BUTTON);
		if (flag1) {
			TestNGCustomReporter.logbr("logout link is displayed on Home page");
		} else {
			TestNGCustomReporter.logbr("logout link is not displayed on Home page");
		}
		boolean flag2 = verifyWebElement(HOMEPAGE_CHANGE_PASSWORD);

		if (flag2) {
			TestNGCustomReporter.logbr("change password is displayed on Home page");
		} else {
			TestNGCustomReporter.logbr("change password  is not displayed on Home page");
		}
		return (flag1 && flag2);
	}

	public boolean verifyContentPageButton() {

		boolean flag = verifyElementSelected(PAGEBUILDER_CONTENT_PAGERBTN);
		if (flag) {
			TestNGCustomReporter.logbr("Content Page RadioButton default is  selected on  PageBuilder page");
		} else {
			TestNGCustomReporter.logbr("Content Page RadioButton default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	public boolean verifyCampaignPageButton() {

		boolean flag = verifyWebElement(PAGEBUILDER_CAMPAIGN_PAGEBTN);
		if (flag) {
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton default is  selected on  PageBuilder page");

			clickElement(PAGEBUILDER_CAMPAIGN_PAGEBTN);
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton  is  selected on  PageBuilder page");

		} else {
			TestNGCustomReporter.logbr("Content Page RadioButton default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	public boolean verifyTwocoloumnRadioButton() {

		boolean flag = verifyElementSelected(PAGEBUILDER_COMMON_2COLUMN_RADIO_BTN);
		if (flag) {
			TestNGCustomReporter.logbr("Two Coloumn  Radio Button default is  selected on  PageBuilder page");

		} else {
			TestNGCustomReporter.logbr("Two Coloumn  Radio default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	public boolean verifyOnecoloumnRadioButton() {

		boolean flag = verifyElementSelected(PAGEBUILDER_COMMON_1COLUMN_RADIO_BTN);
		if (flag) {
			TestNGCustomReporter.logbr("Two Coloumn  Radio Button default is  selected on  PageBuilder page");

		} else {
			TestNGCustomReporter.logbr("Two Coloumn  Radio default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	public boolean verifyAboutusPageButton() {

		boolean flag = verifyWebElement(PAGEBUILDER_CAMPAIGN_PAGEBTN);
		if (flag) {
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton default is  selected on  PageBuilder page");

			clickElement(PAGEBUILDER_ABOUTUS_PAGE_BTN);
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton  is  selected on  PageBuilder page");

		} else {
			TestNGCustomReporter.logbr("Content Page RadioButton default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	public boolean verifySpecialPageButton() {

		boolean flag = verifyWebElement(PAGEBUILDER_SPECIAL_PAGE);
		if (flag) {
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton default is  selected on  PageBuilder page");

			clickElement(PAGEBUILDER_SPECIAL_PAGE);
			TestNGCustomReporter.logbr("CampaignPage Page RadioButton  is  selected on  PageBuilder page");

		} else {
			TestNGCustomReporter.logbr("Content Page RadioButton default is not selected on  PageBuilder page");
		}
		return (flag);
	}

	
	public boolean verifyformPoistionButton() {

		boolean flag = verifyElementSelected(CAMPAIGNPAGE_FORMPOISTION_LEFT);
		if (flag) {
			TestNGCustomReporter.logbr("Upper Right Overlay radio button  is  selected default on  formpoistion");
		} else {
			TestNGCustomReporter.logbr("Upper Right Overlay radio button default is not selected on  formpoistion");
		}
		return (flag);
	}
	
	
	public boolean selectBelowContent(){
		clickElement(CAMPAIGNPAGE_FORMPOISTION_BELOWCONTENT);
		return true;
	}
	
	public boolean selectBelowImage(){
		clickElement(CAMPAIGNPAGE_FORMPOISTION_BELOWIMAGE);
		return true;
	}
	
	
	
	public boolean verifyformPoistionButtons() {

		boolean flag1 = verifyWebElement(CAMPAIGNPAGE_FORMPOISTION_BELOWIMAGE);
		if (flag1) {
			TestNGCustomReporter.logbr("Below Campaign Image radio button  displayed ");
		} else {
			TestNGCustomReporter.logbr("Below Campaign Image radio button  displayedis not displayed");
		}

		boolean flag2 = verifyWebElement(CAMPAIGNPAGE_FORMPOISTION_BELOWCONTENT);
		if (flag2) {
			TestNGCustomReporter.logbr("Below content radio button displayed");
		} else {
			TestNGCustomReporter.logbr("Below content radio button is not displayed");
		}
		return (flag1 && flag2);
	}
	
	
	public boolean selectDepartmentdropdownitem(String option) {
		boolean flag = false;
		// clickElement(PAGEBUILDER_DEPARTMENT_DROPDOWN);PAGEBUILDER_DEPARTMENT_DROPDOWN_LIST
		List<WebElement> elements = returnWebElements(PAGEBUILDER_DEPARTMENT_DROPDOWN_LIST);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.department_name = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean selectformCategorydropdownitem(String option) {
		boolean flag = false;
		clickElement(PAGEBUILDER__FORMCATEGORY_DROPDOWN_LIST);
		List<WebElement> elements = returnWebElements(PAGEBUILDER_COMMON_FORMCATEGORY_LABELS);
		for (WebElement el : elements) {
			if (el.getText().trim().equalsIgnoreCase(option)) {
				el.click();
				flag = true;
				this.department_name = el.getText();
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public boolean resposniveContent(String content) {
		switchToFrame(PAGEBUILDER_COMMON_RESPONSIVEFRAME3);
		clickElement(PAGEBUILDER_COMMON_RESPONSIVEBODY);
		enterValue(PAGEBUILDER_COMMON_RESPONSIVEBODY, content);
		getWebDriver().switchTo().defaultContent();
		return true;
	}

	public boolean editResposniveContent(String content) {
		switchToFrame(PAGEBUILDER_COMMON_RESPONSIVEFRAME1);
		clickElement(PAGEBUILDER_COMMON_RESPONSIVEBODY);
		enterValue(PAGEBUILDER_COMMON_RESPONSIVEBODY, content);
		getWebDriver().switchTo().defaultContent();
		return true;
	}

	public boolean resposniveEnglishContent(String content) {
		switchToFrame(PAGEBUILDER_COMMON_RESPONSIVEFRAME2);
		clickElement(PAGEBUILDER_COMMON_RESPONSIVEBODY);
		enterValue(PAGEBUILDER_COMMON_RESPONSIVEBODY, content);
		getWebDriver().switchTo().defaultContent();
		return true;
	}

	public SCPages savePage() {
		clickElement(PAGEBUILDER_COMMON_SAVEBUTTON);
		waitforPageTolaod(10);
		return this;
	}

	public SCPages campsavePage() {
		clickElement(PAGEBUILDER_COMMON_CAMPSAVEBUTTON);
		waitforPageTolaod(1);
		return this;
	}
	
	public boolean veirfyPageTitle(String title) {
		boolean flag = getWebDriver().getTitle().equalsIgnoreCase(title);
			return flag;
	}

	public boolean verifylogoutButtonFunctionality() {
		boolean flag = false;
		clickElement(HOMEPAGE_LOGOUT_BUTTON);
		String title = getWebDriver().getTitle();

		if (title.equalsIgnoreCase("admin_page_title")) {
			flag = true;
			TestNGCustomReporter.logbr(" logout sucessfull and Application bring back to login page");
		} else {
			flag = false;
			TestNGCustomReporter.logbr(" logout is Unsuccessfull");
		}
		return flag;
	}

	public boolean veirfyFrontEndPageTitle(String Title) {
		return verifyExpectedText(PAGEBUILDER_FRONTEND_CONTENTPAGE, Title);

	}

	public boolean veirfyEditorText(String text) {
		return verifyExpectedText(PAGEBUILDER_CONTENT_PAGE_EDITERTEXT, text);

	}

	public boolean descriptionLinkButton() {
		clickElement(PAGEBUILDER_COMMOM_DESCRPTION_LINK);
		clickElement(PAGEBUILDER_COMMOM_DESCRPTION_CLOSE);
		return true;
	}

	public boolean verifyUrlPatternGroup(String replaceValue) {
		clickElement(PAGEBUILDER_COMMON_URLPATTERN_CLICK_TAB3);
		return verifyExpectedText(PAGEBUILDER_COMMON_URL_PATTERNSECTION, replaceValue);

	}

	public boolean verifyEditUrl(String placeholderValue, String replaceValue) {
		replaceValueClikElement(PAGEBUILDER_COMMON_EDIT_URL, placeholderValue, replaceValue);
			return true;

	}

	public boolean verifyEditUrl1(String replaceValue) {
		enterValue(PAGEBUILDER_COMMON_EDIT_URL, replaceValue);
		return true;

	}

	public boolean verifyUrlPattern(String replaceValue) {
			clickElement(PAGEBUILDER_COMMON_URLPATTERN_CLICK_TAB2);
			return verifyExpectedText(PAGEBUILDER_COMMON_URL_PATTERNSECTION, replaceValue);

	}

	public boolean verifyformName(String placeholderValue, String replaceValue) {
			replaceValueClikElement(CAMPAIGNPAGE_FORMNAME_TEXT, placeholderValue, replaceValue);
			return true;

	}
	public boolean verifyformName1(String expected) {
				return verifyExpectedText(CAMPAIGNPAGE_FORMNAME_TEXT, expected);

	}
	public boolean headerTextValue(String placeholderValue,String replaceValue) {
		replaceValueClikElement(CAMPAIGNPAGE_FORM_HEADER_TEXT, placeholderValue, replaceValue);
		return true;
		
	}
	public boolean submitheaderTextValue(String Value) {
		enterValue(CAMPAIGNPAGE_FORM_HEADER_TEXT, Value);
		return true;
	}
	public boolean actionButton(String placeholderValue,String replaceValue) {
		replaceValueClikElement(CAMPAIGNPAGE_FORM_ACTION_BUTTON_TEXT, placeholderValue, replaceValue);
		return true;
		
	}
	public boolean actionTextValue(String Value) {
		enterValue(CAMPAIGNPAGE_FORM_ACTION_BUTTON_TEXT, Value);
		return true;
	}
	public boolean campaignFormFields() {
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_PARTYINVITAION);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_NOOFPERSONS);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_CITY);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_ZIP);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_ZIPVALREQ);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_COMPANYNAME);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_NAMEREQ);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_COMMENTS);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_ADDRESS);
		clickElement(CAMPAIGNPAGE_FORM_CHECKBOX_ADRESSREQ);
		return true;
	}
	public boolean editVerifyUrlPattern(String replaceValue) {
		return verifyExpectedText(PAGEBUILDER_COMMON_URL_PATTERNSECTION, replaceValue);

	}
	public boolean verifySiteMapUrl( ) {
		 getText(PAGEBUILDER_SITEMAP_TEXT);
		 return true;
	}
	public boolean formsubmitButton(String replaceValue) {
		return verifyExpectedText(CAMPAIGNPAGE_FORM_SUBMITBUTTON, replaceValue);

	}
	public boolean uploadFileButton( ){
		clickElement(CAMPAIGNPAGE_FORM_FILE_UPLOADBUTTON);
			waitforPageTolaod(1);
		return true;
	}
	
	public  void uploadFileWithAutoIt (String Path) throws IOException {
		Runtime.getRuntime().exec(Path);
		
	 }
	

	 public synchronized void uploadFileWithRobot (String imagePath) {
		 {
			 synchronized (imagePath) {
			}
		   StringSelection stringSelection = new StringSelection(imagePath);
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(stringSelection, null);
	 
	        Robot robot = null;
	 
	        try {
	            robot = new Robot();
	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	        synchronized (robot) {
				
	     robot.delay(250);
	     
	     robot.keyPress(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
           /* robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(150);
	        robot.keyRelease(KeyEvent.VK_ENTER);*/
	     
	        }
	    }}
}

	 












