package com.r1v2.frontend.Modules.Oem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.PropertyFileUtil;
import com.core.util.Utility;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class DeletedFrontendCampPageOem extends BaseTest {
	public static final Logger logger = LogManager.getLogger(DeletedFrontendCampPageOem.class);
	DataBase database;
	SCPages pages;

	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();
	CSVTableRow loginData = login.get(0);

	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".CampaignPageFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	// CSVTableRow pagesdata = page.get(0);

	String regiondatabase = td.get(propUtil.getString("region") + ".env");
	// String dealerID=pagesdata.getString("DealerId");
	String userID = loginData.getString("UserId");
	Map<String, CSVTableRow> csvData = new HashMap<>();

	@Test(priority = 1)
	public void deleteFrontendOemCampaign() throws InterruptedException {

		Iterator<CSVTableRow> itr = page.iterator();
		while (itr.hasNext()) {
			CSVTableRow currentRow = itr.next();
			String level = currentRow.getString("Level");
			if ("FrontendUpdatedOem".equalsIgnoreCase(level)) {

				String dealerID = currentRow.getString("DealerId");
				extentTest = report.createTest(getClass().getName());
				database = getPageFactory().databse();
				pages = getPageFactory().scHomePage();

				String processqueQuery = "select  module_id  from  process_que where  page_type='CONT'"
						+ "and change_flag=1 and   fk_dealer_id=" + dealerID + "";
				Utility.processQue(processqueQuery);
			}
		}
		Iterator<CSVTableRow> pageItr = page.iterator();
		while (pageItr.hasNext()) {
			CSVTableRow currentRow = pageItr.next();
			String level = currentRow.getString("Level");
			csvData.put(level, currentRow);
		}
	}

	@Test(priority = 2, dependsOnMethods = { "deleteFrontendOemCampaign" })
	public void deleteFrontendOemCampaign_1() {
		CSVTableRow currentRow = csvData.get("FrontendUpdatedOem");
		String dealerID = currentRow.getString("DealerId");
		String query = "select page_url from izmoweb_r1v2.idw_dealer_webpages where  page_type='CAMP' and"
				+ " fk_make_id=" + dealerID + " and user_added=" + userID + " and status='DELE'";

		String pageURL = database.executeSQLQuery(regiondatabase, query);
		System.out.println(pageURL);
		pages.frontendUrl(currentRow.getString("Dealers") + pageURL);
	}

	@Test(priority = 2)
	public void deleteFrontendOemCampaign_2() {
		boolean actual = pages.verifySiteMapUrl();
		Utility.captureScreenshots(driver, "PageBuilder", "FrotnendDeleteContentPageOEM");
		Assert.assertEquals(actual, true, " Deleted Campaign  Page is not Loading  FrontEnd in OEM Level ");
	}

	@Test(priority = 3)
	public void deleteFrontendOemCampaign_3() {
		boolean actual = pages.browserClose();
		Assert.assertEquals(actual, true, " Close the browser ");
	}
}
