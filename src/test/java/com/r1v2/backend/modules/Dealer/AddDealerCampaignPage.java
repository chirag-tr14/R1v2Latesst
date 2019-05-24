package com.r1v2.backend.modules.Dealer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.PropertyFileUtil;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class AddDealerCampaignPage extends BaseTest {

	public static final Logger logger = LogManager.getLogger(AddDealerCampaignPage.class);
	DataBase database = getPageFactory().databse();
	SCPages scpages;
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".CampaignPageFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	String regiondatabase = td.get(propUtil.getString("region") + ".env");

	Map<String, CSVTableRow> csvData = new HashMap<>();
	String region = propUtil.getString("region");

	private static String uploadpathfilePath = System.getProperty("user.dir")
			+ "\\AutoIt\\ImageUpload.exe";
	
	
	@BeforeClass
	public void addDlrCampaignPage_1() {
		extentTest = report.createTest(getClass().getName());
		Iterator<CSVTableRow> itr = login.iterator();
		while (itr.hasNext()) {
			CSVTableRow currentRow = itr.next();
			String user = currentRow.getString("User");
			if ("rajesh".equalsIgnoreCase(user)) {
				scpages = getPageFactory().scHomePage();
				scpages.openSCLoginpage();
				scpages.goToHomePage(currentRow.getString("admin_username"), currentRow.getString("admin_password"));
			} else {
			}
		}
		Iterator<CSVTableRow> pageItr = page.iterator();
		while (pageItr.hasNext()) {
			CSVTableRow currentRow = pageItr.next();
			String level = currentRow.getString("Level");
			csvData.put(level, currentRow);
			
		}

	}

	@Test(priority = 1)
	public void addDlrCampaignPage_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void addDlrCampaignPage_3() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.selectOrganization().selectDealer(currentRow.getString("Dealers"));
		scpages.navigatePageBuilder();
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page on  Dealer  Level");
	}

	@Test(priority = 3)
	public void addDlrCampaignPage_4() {
		boolean actual = scpages.verifyCampaignPageButton();
		scpages.clickFirstTabNextBtn();
		Assert.assertEquals(actual, true, " Next Button is not clikable in Dealer Level ");
	}

	@Test(priority = 4)
	public void addDlrCampaignPage_5() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.verifyMandatoryFieldSecondTab();
		scpages.pageTitle(currentRow.getString("Title"));
		scpages.verifyMandatoryFieldSecondTab();
		scpages.pageUrl(currentRow.getString("Url"));
		Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page ");

	}

	@Test(priority = 5)
	public void addDlrCampaignPage_6() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages
				.verifyUrlPattern(currentRow.getString("Url").concat(currentRow.getString("UrlPattern").trim()));
		Assert.assertEquals(actual, true, " Url pattern is not Matching as per local ");
	}

	@Test(priority = 6)
	public void addDlrCampaignPage_7() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.selectDepartmentdropdownitem(currentRow.getString("Department"));
		scpages.verifyTwocoloumnRadioButton();
		scpages.clickSecondTabNextBtn();

		Assert.assertEquals(actual, true, " Selecting Department for in Dealer Level  ");
	}

	@Test(priority = 7)
	public void addDlrCampaignPage_8() throws IOException {
		// CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.uploadFileButton();
		scpages.uploadFileWithAutoIt(uploadpathfilePath);
		//scpages.uploadFileWithRobot(filePath);
		scpages.clickThirdTabNextBtn();
		Assert.assertEquals(actual, true, " Uploading Image on Dealer Level");
	}

	@Test(priority = 8)
	public void addDlrCampaignPage_9() {
		CSVTableRow currentRow = csvData.get("Dealer");
		if (region.equalsIgnoreCase("euro")) {
			boolean actual = scpages.selectformCategorydropdownitem((currentRow.getString("Form_category")));
			scpages.verifyformName("@Value", currentRow.getString("LocalId"));
			scpages.verifyformName1(currentRow.getString("Title").concat(currentRow.getString("FormValue")));
			scpages.verifyformPoistionButton();
			scpages.verifyformPoistionButtons();
			scpages.clickFifthTabNextBtn();
			Assert.assertEquals(actual, true, " Form Name is not appeanding Campaign Form  ");
		}

		else {
			boolean actual = scpages.verifyformName("@Value", currentRow.getString("LocalId"));
			scpages.verifyformName1(currentRow.getString("Title").concat(currentRow.getString("FormValue")));
			scpages.verifyformPoistionButton();
			scpages.verifyformPoistionButtons();

			scpages.headerTextValue("@Value", currentRow.getString("LocalId"));
			scpages.submitheaderTextValue(currentRow.getString("FormValue"));
			scpages.actionButton("@Value", currentRow.getString("LocalId"));
			scpages.actionTextValue(currentRow.getString("Title"));
			scpages.campaignFormFields();
			scpages.clickFifthTabNextBtn();
			Assert.assertEquals(actual, true, " Form Name is not appeanding Campaign Form  ");
		}
	}

	@Test(priority = 10)
	public void addDlrCampaignPage_12() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.descriptionLinkButton();
		scpages.resposniveEnglishContent(currentRow.getString("Responsive_Content"));
		scpages.clickSixthTabNextBtn();
		scpages.clickSeventhTabNextBtn();
		Assert.assertEquals(actual, true, " Passing Responsive Content REsponsive Editor ");
	}

	@Test(priority = 10)
	public void addDlrCampaignPage_13() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.campsavePage().veirfyPageTitle(currentRow.getString("PageTitle"));
		Assert.assertEquals(actual, true, " Campaign Page is not saved on Dealer Level");
	}

	@Test(priority = 11)
	public void addDlrCampaignPage_14() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}

}
