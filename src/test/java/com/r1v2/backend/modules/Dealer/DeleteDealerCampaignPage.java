package com.r1v2.backend.modules.Dealer;

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

public class DeleteDealerCampaignPage extends BaseTest {
	public static final Logger logger = LogManager.getLogger(DeleteDealerCampaignPage.class);
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
	public void dlrDeleteCampPage_1() {
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
	public void dlrDeleteCampPage_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void dlrDeleteCampPage_3() {
			CSVTableRow currentRow = csvData.get("EditDealer");
				boolean actual = 
				scpages.selectOrganization().selectDealer(currentRow.getString("Dealers"));
				scpages.navigateDeletePageBuilder(currentRow.getString("Url"));
				Utility.captureScreenshots(driver,"PageBuilder" ,"DeleteContentPageDealer");
				Assert.assertEquals(actual, true, " Searching Campaign Page url in PageBuilder for Dealer Level ");
			}
	

	@Test(priority = 3)
	public void dlrDeleteCampPage_4() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}
}
