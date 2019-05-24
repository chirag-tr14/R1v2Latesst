package com.r1v2.frontend.Modules.Dealer;

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

public class UpdatedFrontendCampPageDealer extends BaseTest {
	public static final Logger logger = LogManager.getLogger(UpdatedFrontendCampPageDealer.class);
	DataBase database;
	SCPages pages;

	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();
	CSVTableRow loginData = login.get(0);
	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".CampaignPageFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	String regiondatabase = td.get(propUtil.getString("region") + ".env");
	String userID = loginData.getString("UserId");
	Map<String, CSVTableRow> csvData = new HashMap<>();
	String region = propUtil.getString("region");
	String processqueQuery;

	@Test(priority=1)
	public void updateFrontendDlrCampaign() throws InterruptedException {

		Iterator<CSVTableRow> itr = page.iterator();
		while (itr.hasNext()) {
			CSVTableRow currentRow = itr.next();
			String level = currentRow.getString("Level");
			if ("FrontendUpdatedDlr".equalsIgnoreCase(level)) {
				String dealerID = currentRow.getString("DealerId");
				extentTest = report.createTest(getClass().getName());
				database = getPageFactory().databse();
				pages = getPageFactory().scHomePage();

				if (region.equalsIgnoreCase("euro")) {
					processqueQuery = "select  module_id  from  process_que where  page_type='CAMP'"
							+ "and change_flag=1 and   fk_dealer_id=" + dealerID + "";
					String proceesque = database.executeSQLQuery(regiondatabase, processqueQuery);
					
					String formbuilderModuleid = "select value_var from idw_dealer_webpages_variables where "
							+ "user_added="+userID+"  and key_var='FormId'and fk_webpage_id=".concat(proceesque);
					
					String proceesque1 = database.executeSQLQuery(regiondatabase, formbuilderModuleid);
					
					String processqueQuery = "select * from  process_que where  page_type='FORM'"
							+ "and change_flag=1 and   fk_dealer_id=" + dealerID
							+ " and module_id=".concat(proceesque1);
					Utility.processQue(processqueQuery);
				}
				else {
					processqueQuery = "select  module_id  from  process_que where  page_type='CAMP'"
							+ "and change_flag=1 and   fk_dealer_id=" + dealerID + "";
					Utility.processQue(processqueQuery);
				 }
			}
		}
		Iterator<CSVTableRow> pageItr = page.iterator();
		while (pageItr.hasNext()) {
			CSVTableRow currentRow = pageItr.next();
			String level = currentRow.getString("Level");
			csvData.put(level, currentRow);
		}
	}

	@Test( priority=2,dependsOnMethods = { "updateFrontendDlrCampaign" })
	public void updateFrontendDlrCampaign_1() {
		CSVTableRow currentRow = csvData.get("FrontendDlr");
		String dealerID = currentRow.getString("DealerId");
		String query = "select page_url from izmoweb_r1v2.idw_dealer_webpages where  page_type='CAMP' and"
				+ " fk_dealer_id=" + dealerID + " and user_added=" + userID + " and status='ACTV'";
		String pageURL = database.executeSQLQuery(regiondatabase, query);
		System.out.println(pageURL);
		pages.frontendUrl(currentRow.getString("Dealers") + pageURL);
	}

	@Test(priority = 2)
	public void updateFrontendDlrCampaign_2() {
		CSVTableRow currentRow = csvData.get("FrontendUpdatedDlr");
		boolean actual = pages.veirfyFrontEndPageTitle(currentRow.getString("Title"));
		pages.veirfyEditorText(currentRow.getString("Responsive_Content"));
		Utility.captureScreenshots(driver, "PageBuilder", "FrontendCampaignPageDealer");
		Assert.assertEquals(actual, true, " Updated Campaign Page is Loading in  FrontEnd  in Dealer Level");
	}

	@Test(priority = 3)
	public void updateFrontendDlrCampaign_3() {
		boolean actual = pages.browserClose();
		Assert.assertEquals(actual, true, " Close the browser ");
	}
}
