package com.r1v2.backend.modules.Oem;

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
import com.core.util.Utility;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class AddOemContentPage extends BaseTest {

	public static final Logger logger = LogManager.getLogger(AddOemContentPage.class);
	SCPages scpages;
	DataBase database = getPageFactory().databse();
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	// String User = currentRow.getString("User");
	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".ContentPageFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();

	String region = propUtil.getString("region");
	String regiondatabase = td.get(propUtil.getString("region") + ".env");
	String csv = td.get(propUtil.getString("region") + ".ContentPageFilePath");

	Map<String, CSVTableRow> csvData = new HashMap<>();

	@BeforeClass
	public void addoemContentPG_1() {
		extentTest = report.createTest(getClass().getSimpleName());
		Iterator<CSVTableRow> itr = login.iterator();
		while (itr.hasNext()) {
			CSVTableRow currentRow = itr.next();
			String user = currentRow.getString("User");
			if ("rajesh".equalsIgnoreCase(user)) {
				scpages = getPageFactory().scHomePage();
				scpages.openSCLoginpage();
				scpages.goToHomePage(currentRow.getString("admin_username"), currentRow.getString("admin_password"));
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
	public void addoemContentPG_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void addoemContentPG_3() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.selectOrganization().selectOEM("@Value", currentRow.getString("DealerId"));
		scpages.navigatePageBuilder();
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page in OEM Level ");
	}

	@Test(priority = 3)
	public void addoemContentPG_4() {
		boolean actual = scpages.clickFirstTabNextBtn();
		Assert.assertEquals(actual, true, " Next Step Button is not clickable");
	}

	@Test(priority = 4)
	public void addoemContentPG_5() {
		if (region.equalsIgnoreCase("india")) {
			CSVTableRow currentRow = csvData.get("OEM");
			boolean actual = scpages.verifyMandatoryFieldSecondTab();
			scpages.pageTitle(currentRow.getString("Title"));
			scpages.verifyMandatoryFieldSecondTab();
			scpages.pageUrl(currentRow.getString("Url"));
			Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page ");
		}

		else {
			CSVTableRow currentRow = csvData.get("OEM");
			boolean actual = scpages.verifyMandatoryFieldSecondTab();
			scpages.pageTitle(currentRow.getString("Title"));
			scpages.verifyMandatoryFieldSecondTab();
			scpages.englishPageTitle(currentRow.getString("Title1"));
			scpages.verifyMandatoryFieldSecondTab();
			scpages.pageUrl(currentRow.getString("Url"));
			scpages.verifyMandatoryFieldSecondTab();
			scpages.englishPageUrl(currentRow.getString("Url1"));
			Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page ");
		}
	}

	@Test(priority = 5)
	public void addoemContentPG_6() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual =
					scpages.verifyUrlPattern(currentRow.getString("Url").concat(currentRow.getString("UrlPattern").trim()));
		Assert.assertEquals(actual, true, " Url pattern is not Matching as per local ");
	}

	@Test(priority = 6)
	public void addoemContentPG_7() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.verifyTwocoloumnRadioButton();
		scpages.selectDepartmentdropdownitem(currentRow.getString("Department"));
		Assert.assertEquals(actual, true, " Selecting Department in OEM Level ");
	}

	@Test(priority = 7)
	public void addoemContentPG_8() {
		boolean actual = scpages.clickSecondTabNextBtn();
		scpages.descriptionLinkButton();
		Assert.assertEquals(actual, true, " Description Button is Clickable ");
	}

	@Test(priority = 8)
	public void addoemContentPG_9() {
		if (region.equalsIgnoreCase("india")) {
			CSVTableRow currentRow = csvData.get("OEM");
			boolean actual = scpages.resposniveEnglishContent(currentRow.getString("Responsive_Content"));
			Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page ");
		}

		else {
			CSVTableRow currentRow = csvData.get("OEM");
			boolean actual = scpages.resposniveContent(currentRow.getString("Responsive_Content"));
			// scpages.resposniveEnglishContent(currentRow.getString("Responsive_Content1"));
			Assert.assertEquals(actual, true, " Passing data in Resposnive Editor in OEM Level ");
		}
	}

	@Test(priority = 9)
	public void addoemContentPG_10() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.savePage().veirfyPageTitle(currentRow.getString("PageTitle"));
		Utility.captureScreenshots(driver,"PageBuilder" ,"AddContentPageOEM");
		Assert.assertEquals(actual, true, " Content Page saved in  OEM Level");
	}

	@Test(priority = 10)
	public void addoemContentPG_11() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}
}

