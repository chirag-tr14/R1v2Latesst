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

public class EditOemContentPage extends BaseTest {
	public static final Logger logger = LogManager.getLogger(EditOemContentPage.class);
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
	public void editOemContentpagePG_1() {
		extentTest = report.createTest(getClass().getCanonicalName());
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
	public void editoemContentPG_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void editoemContentPG_3() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.selectOrganization().selectOEM("@Value", currentRow.getString("DealerId"));
		scpages.navigateEditPageBuilder(currentRow.getString("Url"));
		scpages.verifyEditFrenchTitle("@Value", currentRow.getString("Title"));
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page in OEM Level ");
	}

	@Test(priority = 3)
	public void editoemContentPG_4() {
		CSVTableRow currentRow = csvData.get("EditOem");
		if (region.equalsIgnoreCase("india")) {
			scpages.pageTitle(currentRow.getString("Title"));
		}
		else{
			boolean actual = scpages.pageTitle(currentRow.getString("Title1"));
		scpages.verifyEditFrenchTitle1("@Value", currentRow.getString("Title"));
		Assert.assertEquals(actual, true, " Title is Updateing in Title Field ");
	}
	}
	@Test(priority = 4)
	public void editoemContentPG_5() {
		CSVTableRow currentRow = csvData.get("EditOem");
		if (region.equalsIgnoreCase("india")) {
			
		}
		else {
			boolean actual = scpages.englishPageUrl(currentRow.getString("Url1"));
		Assert.assertEquals(actual, true, " Title is Updateing in Title Field ");
	}
	}

	@Test(priority = 5)
	public void editoemContentPG_6() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.verifyEditUrl("@Value", currentRow.getString("Url"));
		Assert.assertEquals(actual, true, "Search Campaign Page URL in OEM Level  ");
	}

	@Test(priority = 6)
	public void editoemContentPG_7() {
		CSVTableRow currentRow = csvData.get("EditOem");
		boolean actual = scpages.verifyEditUrl1(currentRow.getString("Url"));
		scpages.selectOneColumnLayout();
		Assert.assertEquals(actual, true, " Title is Updating in Title Field ");
	}

	@Test(priority = 7)
	public void editoemContentPG_8() {
		CSVTableRow currentRow = csvData.get("EditOem");
		boolean actual = scpages.selectDepartmentdropdownitem(currentRow.getString("Department"));
		scpages.clickThirdTabNextBtn();
		Assert.assertEquals(actual, true, " Selecting Department in  OEM Level ");
	}

	@Test(priority = 8)
	public void editoemContentPG_9() {
		CSVTableRow currentRow = csvData.get("EditOem");
		if (region.equalsIgnoreCase("india")) {
			boolean actual = scpages.descriptionLinkButton();
			scpages.editResposniveContent(currentRow.getString("Responsive_Content"));
			Assert.assertEquals(actual, true, " Updating data in Responsive Ediotr in OEM Level ");
		}
		else {
			boolean actual = scpages.descriptionLinkButton();
		scpages.resposniveEnglishContent(currentRow.getString("Responsive_Content"));
		Assert.assertEquals(actual, true, " Updating data in Responsive Ediotr in OEM Level ");
	}
	}
	@Test(priority = 9)
	public void editoemContentPG_10() {
		CSVTableRow currentRow = csvData.get("OEM");
		boolean actual = scpages.savePage().veirfyPageTitle(currentRow.getString("PageTitle"));
		Utility.captureScreenshots(driver,"PageBuilder" ,"UpdateContentPageOEM");
		Assert.assertEquals(actual, true, " Content Page is saved in  OEM Level");
	}

	@Test(priority = 10)
	public void editoemContentPG_11() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}
}
