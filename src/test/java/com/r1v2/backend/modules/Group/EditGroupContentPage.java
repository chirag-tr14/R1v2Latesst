package com.r1v2.backend.modules.Group;

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

public class EditGroupContentPage extends BaseTest {
	public static final Logger logger = LogManager.getLogger(EditGroupContentPage.class);
	SCPages scpages;
	DataBase database = getPageFactory().databse();
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	// String User = currentRow.getString("User");
	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".CampaignPageFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();

	String regiondatabase = td.get(propUtil.getString("region") + ".env");
	String csv = td.get(propUtil.getString("region") + ".CampaignPageFilePath");
	Map<String, CSVTableRow> csvData = new HashMap<>();
	
	@BeforeClass
	public void groupEditContentPage_1() {
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
	public void groupEditContentPage_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void groupEditContentPage_3() {
			CSVTableRow currentRow = csvData.get("Group");
				boolean actual = 
				scpages.selectOrganization().selectGroup("@Value",currentRow.getString("DealerId"));
				scpages.navigateEditPageBuilder(currentRow.getString("Url"));
				scpages.clickFirstTabNextBtn1();
				Assert.assertEquals(actual, true, " Navigate to PageBuilder Page in Group Level ");
			}
	
	@Test(priority = 3)
	public void groupEditContentPage_4() {
		CSVTableRow currentRow = csvData.get("EditGroup");
				boolean actual = scpages.pageTitle(currentRow.getString("Title"));
				Assert.assertEquals(actual, true, " Title is Updateing in Title Field ");
			}

	
	@Test(priority = 4)
	public void groupEditContentPage_5() {
		CSVTableRow currentRow = csvData.get("Group");
				boolean actual = scpages.verifyEditUrl("@Value", currentRow.getString("Url"));
				Assert.assertEquals(actual, true, " Search Campaign Page  Url in Group Level ");
			}
	

	@Test(priority = 5)
	public void groupEditContentPage_6() {
		CSVTableRow currentRow = csvData.get("EditGroup");
				boolean actual = scpages.verifyEditUrl1(currentRow.getString("Url"));
				scpages.selectOneColumnLayout();
				scpages.editVerifyUrlPattern(currentRow.getString("Url").concat(currentRow.getString("UrlPattern")));
				Assert.assertEquals(actual, true, " Url pattern is not Matching as per local ");
			}
	

	@Test(priority = 6)
	public void groupEditContentPage_7() {
		CSVTableRow currentRow = csvData.get("EditGroup");
				boolean actual = scpages.selectDepartmentdropdownitem(currentRow.getString("Department"));
				scpages.clickThirdTabNextBtn();
				Assert.assertEquals(actual, true, " Selecting Department  in Group Level  ");
			}
	

	@Test(priority = 7)
	public void groupEditContentPage_8() {
		boolean actual = scpages.descriptionLinkButton();
		Assert.assertEquals(actual, true, " Description Button is Not Clickable ");
	}

	@Test(priority = 8)
	public void groupEditContentPage_9() {
		CSVTableRow currentRow = csvData.get("EditGroup");
				boolean actual = scpages.editResposniveContent(currentRow.getString("Responsive_Content"));
				Assert.assertEquals(actual, true, " Updating Data in Responsive Editor for  Group Level ");
			}
	
	@Test(priority = 9)
	public void groupEditContentPage_10() {
		CSVTableRow currentRow = csvData.get("EditGroup");
				boolean actual = scpages.savePage().veirfyPageTitle(currentRow.getString("PageTitle"));
				Utility.captureScreenshots(driver,"PageBuilder" ,"EditContentPageGroup");
				Assert.assertEquals(actual, true, " Updating Content Page in Group Level ");
			}
	
	@Test(priority = 10)
	public void groupEditContentPage_11() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}
}
