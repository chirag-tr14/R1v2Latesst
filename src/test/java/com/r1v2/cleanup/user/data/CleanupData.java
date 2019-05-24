package com.r1v2.cleanup.user.data;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.PropertyFileUtil;
import com.core.util.Utility;
import com.r1v2.common.BaseTest;

public class CleanupData   {
	
	BaseTest t = new BaseTest();
	Map<String, String> td =t.getTestDataProperties();
	PropertyFileUtil propUtil = new PropertyFileUtil("config");
	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();
	CSVTableRow logindata = login.get(0);
	String user = logindata.getString("UserId");
	
	@Test(priority = 1)
	public void pageBuilderModule() throws InterruptedException {

		String queri1 = "delete from izmoweb_r1v2.page_dealer_map where user_created=" + user + "";
		// database.executeSQLQuery(regiondatabase, queri1);
		Utility.cleanupData(queri1);

		String quer2 = "delete from idw_dealer_webpages where user_added=" + user + "";
		// database.executeSQLQuery(regiondatabase, quer2);

		Utility.cleanupData(quer2);

	}

}
