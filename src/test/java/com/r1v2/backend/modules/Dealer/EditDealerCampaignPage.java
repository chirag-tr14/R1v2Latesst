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

public class EditDealerCampaignPage extends BaseTest {

	public static final Logger logger = LogManager.getLogger(EditDealerCampaignPage.class);
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

	//private static String filePath = System.getProperty("user.dir") + "\\Images\\207cc-1036-16411.jpg";
	
	private static String uploadpathfilePath = System.getProperty("user.dir")
			+ "\\AutoIt\\ImageUpload1.exe";

	@BeforeClass
	public void dlrCampaignPage_1() {
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
	public void dlrCampaignPage_2() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual, true, "is not displayed on SC Home Page");
	}

	@Test(priority = 2)
	public void dlrEditCampaignPage_3() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.selectOrganization().selectDealer(currentRow.getString("Dealers"));
		scpages.navigateEditPageBuilder(currentRow.getString("Url"));
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page in  Dealer Level ");
	}

	@Test(priority = 3)
	public void dlrEditCampaignPage_4() {
		CSVTableRow currentRow = csvData.get("EditDealer");
		boolean actual = scpages.pageTitle(currentRow.getString("Title"));
		Assert.assertEquals(actual, true, " Title is Updateing in Title Field ");
	}

	@Test(priority = 4)
	public void dlrEditCampaignPage_5() {
		CSVTableRow currentRow = csvData.get("Dealer");
		boolean actual = scpages.verifyEditUrl("@Value", currentRow.getString("Url"));
		Assert.assertEquals(actual, true, " Searching Campaign Page Url in Dealer Level ");
	}

	@Test(priority = 5)
	public void dlrEditCampaignPage_6() {
		CSVTableRow currentRow = csvData.get("EditDealer");
		boolean actual = scpages.verifyEditUrl1(currentRow.getString("Url"));
		scpages.selectOneColumnLayout();
		scpages.editVerifyUrlPattern(currentRow.getString("Url").concat(currentRow.getString("UrlPattern")));
		Assert.assertEquals(actual, true, " Url pattern is not Matching as per local ");
	}

	@Test(priority = 6)
	public void dlrEditCampaignPage_7() {
		CSVTableRow currentRow = csvData.get("EditDealer");
		boolean actual = scpages.selectDepartmentdropdownitem(currentRow.getString("Department"));
		scpages.clickThirdTabNextBtn();
		Assert.assertEquals(actual, true, " Selecting Department  in Dealer Level  ");

	}

	@Test(priority = 7)
	public void dlrEditCampaignPage_8() throws IOException {
		boolean actual = scpages.uploadFileButton();
		if (region.equalsIgnoreCase("euro")) {
			//scpages.uploadFileWithRobot(filePath);
			//scpages.uploadFileWithRobot(filePath);
			scpages.uploadFileWithAutoIt(uploadpathfilePath);
			scpages.uploadFileWithAutoIt(uploadpathfilePath);
		}
		else{
			//scpages.uploadFileWithRobot(filePath);
			scpages.uploadFileWithAutoIt(uploadpathfilePath);
			}
		scpages.clickFourthTabNextBtn();
		Assert.assertEquals(actual, true, " Uploading image in Editing Campaign Page in Dealer Level");
	}

	@Test(priority = 8)
	public void dlrEditCampaignPage_9() {
		CSVTableRow currentRow = csvData.get("EditDealer");
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
			scpages.selectBelowContent();
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

	@Test(priority = 9)
	public void dlrEditCampaignPage_10() {
		CSVTableRow currentRow = csvData.get("EditDealer");
		boolean actual = scpages.descriptionLinkButton();
		scpages.editResposniveContent(currentRow.getString("Responsive_Content"));
		scpages.clickSixthTabNextBtn();
		scpages.clickSeventhTabNextBtn();
		Assert.assertEquals(actual, true, " Updating  Data in Responsive Editor in Dealer Level  ");
	}

	@Test(priority = 10)
	public void dlrEditCampaignPage_11() {
		CSVTableRow currentRow = csvData.get("EditDealer");
		boolean actual = scpages.campsavePage().veirfyPageTitle(currentRow.getString("PageTitle"));
		Assert.assertEquals(actual, true, " Updated Campaign Page in Dealer Level  ");
	}

	@Test(priority = 11)
	public void dlrEditCampaignPage_12() {
		boolean actual = scpages.logoutAdmin();
		scpages.browserClose();
		Assert.assertEquals(actual, true, "LogOut and Close the browser ");
	}
}
