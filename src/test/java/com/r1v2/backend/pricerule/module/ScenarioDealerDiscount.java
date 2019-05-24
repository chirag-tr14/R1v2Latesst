package com.r1v2.backend.pricerule.module;

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
import com.qa.sc.pageobjects.PriceRulePage;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class ScenarioDealerDiscount extends BaseTest {
	public static final Logger logger = LogManager.getLogger(ScenarioDealerDiscount.class);
	DataBase database=getPageFactory().databse();
	SCPages scpages;
	PriceRulePage pricerule;
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	CSVTable pricerulepage = new CSVTable(td.get(propUtil.getString("region")+".priceRuleFilePath"));
	List<CSVTableRow> price = pricerulepage.getRecords();
	
	//CSVTableRow currentRow = getRowDataForLevel(price, discount);
	//CSVTableRow price = priceruledata.get(1);
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	Map<String, CSVTableRow> csvData = new HashMap<>();
	
	
	@BeforeClass
	public void setUpOnce1() {
		extentTest = report.createTest(getClass().getName());
		Iterator<CSVTableRow> itr = login.iterator();
		while (itr.hasNext()) {
			CSVTableRow currentRow = itr.next();
			String user = currentRow.getString("User");
			if ("rajesh".equalsIgnoreCase(user)) {
			scpages=getPageFactory().scHomePage();
			pricerule=getPageFactory().priceRulePage();
			scpages.openSCLoginpage();
			scpages.goToHomePage(currentRow.getString("admin_username"),currentRow.getString("admin_password"));		
	}
			else {
			}
		}
		
		Iterator<CSVTableRow> pageItr = price.iterator();
		while (pageItr.hasNext()) {
			CSVTableRow currentRow = pageItr.next();
			String discount = currentRow.getString("Discount");
			csvData.put(discount, currentRow);
			
		}
	}
	
	@Test(priority=1)
	public void testTD_1() {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual= scpages.selectOrganization()
				.selectDealer(currentRow.getString("Dealer"));
				pricerule.navigatePriceRulePage();
				Assert.assertEquals(actual, true, " Navigate to PageBuilder Page on  Respective Dealer ");
			}
			
	

	@Test(priority=2)
	public void testTD_2() {
		CSVTableRow currentRow = csvData.get("DealerDiscount");{
						boolean actual=pricerule.selectPriceCategoryDropdownitem(currentRow.getString("Price Category"));
		Assert.assertEquals(actual, true, " Selecting PriceCategory DropDown Field Value ");
		}
	}
		
	
	
	@Test(priority=3)
	public void testTD_3() {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.selectVehicleTypeDropdownitem(currentRow.getString("Vehicle Type"));
		Assert.assertEquals(actual, true, " Selecting VehicleType DropDown Field Value ");
	}
	
		
	
	@Test(priority=4)
	public void testTD_4()   {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.selectProviderDropdownitem(currentRow.getString("Provider"));
		Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}

	
	
	@Test(priority=5)
	public void testTD_5()  {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.selectMakeDropdownitem(currentRow.getString("Make"));
		Assert.assertEquals(actual, true, " Selecting Make DropDown Field Value");
	}
		
	
	
	@Test(priority=6)
	public void testTD_6(){  
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.selectModelDropdownitem(currentRow.getString("Model"));
		Assert.assertEquals(actual, true, " Selecting Model DropDown Field Value");
	}
	
	
	@Test(priority=7)
	public void testTD_7(){  
		CSVTableRow currentRow = csvData.get("DealerDiscount");
			boolean actual=pricerule.selectTrimDropdownitem(currentRow.getString("Trim"));
		Assert.assertEquals(actual, true, " Selecting Trim DropDown Field Value");
	}
	
	
	@Test(priority=8)
	public void testTD_8()  {
				boolean actual=pricerule.startDate();
		 Assert.assertEquals(actual, true, " Passing CurentDate For Start Date Field ");
	}
		
	
	@Test(priority=9)
	public void testTD_9()   {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.selectDiscountDropdownitem(currentRow.getString("Discount Type"));
			Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}
	
	@Test(priority=10)
	public void testTD_10() {
		CSVTableRow currentRow = csvData.get("DealerDiscount");
				boolean actual=pricerule.discountValue(currentRow.getString("DiscountValue"));
		Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}
		
	
	@Test(priority=11)
	public void testTD_11()   {
						boolean actual=pricerule.savePriceRule();
		Assert.assertEquals(actual, true, " Dealer Discount Price is Saved");
	}
	}
	
	/*private CSVTableRow getRowDataForLevel(List<CSVTableRow> dataRows, String discount) {
		for (CSVTableRow row : dataRows) {
			if (discount.equalsIgnoreCase(row.getString("Discount")))
				return row;
		}
		return null;
	}*/

	

