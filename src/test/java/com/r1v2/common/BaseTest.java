package com.r1v2.common;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.core.config.BrowserConfig;
import com.core.config.PropTestdataConfig;
import com.core.maindriver.DriverScript;
import com.core.reports.ExtentReport;
import com.core.reports.TestNGCustomReporter;
import com.core.settings.GlobalSettings;

public class BaseTest extends ExtentReport     {

	public WebDriver driver;
	private DriverScript driverScript;
	private GlobalSettings globalSettings = new GlobalSettings();
	private static HashMap<String, String> testDataProperties =new HashMap<String, String>();
		
		
	@BeforeClass
	public void beforeClass() {
		initWebDriver();
		TestNGCustomReporter.logbr("Browser launched successfully");
	}
	
	/*@AfterClass(alwaysRun = true)
	public void afterClass(){
		closeBrowser();
		TestNGCustomReporter.logbr("Closed all the browsers opened by webdriver.");
	}
*/
	
	
	public WebDriver initWebDriver() {
		try {
			String browser = globalSettings.getBrowser();
			driverScript = new DriverScript(new BrowserConfig(browser));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver = driverScript.getDriverObject();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public PageFactory getPageFactory() {
		return new PageFactory(driver);
	}

	public void closeBrowser(){
		driverScript.stopSelenium(driver);
	}

	
	public HashMap<String, String> getTestDataProperties() {
		if(testDataProperties.size()<=0){
			setTestDataProperties();
		}
		return testDataProperties;
	}

	private void setTestDataProperties() {
		try {
			testDataProperties = new PropTestdataConfig()
			.getWebElementMapping();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*@BeforeSuite
public void init() {
	
	 *//** htmlReporter = new ExtentHtmlReporter (
	 * "F:\\IZMO FrameWork\\com.izomweb.com\\Reports\\Reports.html");*//*
	 
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Report.html");
	report = new ExtentReports();
	report.attachReporter(htmlReporter);
	htmlReporter.setAppendExisting(false);
	
	report.setSystemInfo("OS", "Windows 10");
	report.setSystemInfo("Host Name", "10.125.3.18");
	report.setSystemInfo("Environment", "QA");
	report.setSystemInfo("User Name", "Rajesh");
	
	htmlReporter.config().setChartVisibilityOnOpen(true);
	htmlReporter.config().setDocumentTitle("Extent Report");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.STANDARD);
	
}

@AfterMethod
 public void getResult(ITestResult result) throws IOException {
	
	if (result.getStatus() == ITestResult.FAILURE) {
	    extentTest.log(Status.FAIL, MarkupHelper
				.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
		extentTest.fail(result.getThrowable());
		
	} else if (result.getStatus() == ITestResult.SUCCESS) {
		extentTest.log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		
	} else {
		extentTest.log(Status.SKIP,
				MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		extentTest.skip(result.getThrowable());
		
	}
	
}

@AfterSuite
public void tearDown() {
	report.flush();
	

}

*/

	
