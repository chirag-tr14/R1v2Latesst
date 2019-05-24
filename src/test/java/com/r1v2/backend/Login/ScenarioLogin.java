package com.r1v2.backend.Login;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.PropertyFileUtil;
import com.qa.sc.pageobjects.SCLoginPage;
import com.r1v2.common.BaseTest;

public class ScenarioLogin extends BaseTest {
	private SCLoginPage loginPage ;
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
	CSVTable t = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> records = t.getRecords();
	CSVTableRow record = records.get(0);

	@BeforeClass
	public void setUpOnce() {
			loginPage = getPageFactory().adminLoginPage();
	}

	
	
	
	
	@Test()
	public void testSC_1() {
		extentTest = report.createTest("LoginPage ");
		boolean actual = loginPage.openSCLoginpage().
				veirfyPageTitle(record.getString("admin_page_title"));
			Assert.assertEquals(actual, true, " login page is not displayed");
			//extentTest.log(Status.INFO, "Titile is matched");
	}

	@Test()
	public void testSC_2() {
		
				boolean actual = loginPage.openSCLoginpage()
					.goToHomePage(record.getString("admin_username"), record.getString("admin_password_invalid"))	
					.veirfyPageTitle(record.getString("admin_page_title"));
				//extentTest.log(Status.INFO, "Titile is vdsvdsvs matched");
		Assert.assertEquals(actual, true, " Login page is not displayed");
		
	}

	@Test(priority = 3)
	public void testSC_3() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username_invalid"), record.getString("admin_password"))
				.veirfyPageTitle(record.getString("admin_page_title"));
		//extentTest.log(Status.INFO, "Titile is matched");
				Assert.assertEquals(actual, true, " login page is not displayed");
				//extentTest.log(Status.INFO, "Titile is matched");
	}

	@Test(priority = 4)
	public void testSC_4() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username_invalid"), record.getString("admin_password_invalid"))
				.veirfyPageTitle(record.getString("admin_page_title"));
			Assert.assertEquals(actual, true, " login page is not displayed");
	}

	@Test(priority = 5)
	public void testSC_5() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username_blank"),record.getString("admin_password_invalid"))
				//.accetpAlert()
			    .veirfyPageTitle(record.getString("admin_page_title"));
				Assert.assertEquals(actual, true, " login page is not displayed");
	}

	@Test(priority = 6)
	public void testSC_6() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username_invalid"), record.getString("admin_password"))
				.veirfyPageTitle(record.getString("admin_page_title"));
				Assert.assertEquals(actual, true, " login page is not displayed");

	}
	@Test(priority = 7)
	public void testSC_7() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username"), record.getString("admin_password_blank"))
				//.accetpAlert()
				.veirfyPageTitle(record.getString("admin_page_title"));
		Assert.assertEquals(actual, true, " login page is not displayed");
	}

	@Test(priority = 8)
	public void testSC_8() {
		boolean actual=loginPage
			 .openSCLoginpage()
			 .VerifyhelpTextClikable();
	 Assert.assertEquals(actual, true, " help text  is not displayed");
	}
	
	@Test(priority = 9)
	public void testSC_9() {
		boolean actual = loginPage
				.openSCLoginpage()
				.goToHomePage(record.getString("admin_username"), record.getString("admin_password"))
								.veirfyPageTitle(record.getString("login_page_title"));
			Assert.assertEquals(actual, true, " Login Page is not displayed");
	}
	
	@Test(priority=10)
	public void testPG_10() {
		boolean actual= loginPage.logoutAdmin();
						loginPage.browserClose();			
		    Assert.assertEquals(actual, true, "LogOut and Close the browser ");
    }

}

