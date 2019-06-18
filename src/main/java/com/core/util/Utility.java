package com.core.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

/**
 * Name : Utility
 * 
 * Description : It is an Utility class which will hold all independent utility
 * functions that are used for automation
 * 
 * 
 * 
 * 
 **/
public class Utility {

	public static final Logger logger = LogManager.getLogger(Utility.class);

	
	
	//This Meethod Returns Waiting for ProcessQue
	public static void processQue(String query) throws InterruptedException {
		DataBase database = new DataBase();
		BaseTest t = new BaseTest();
		Map<String, String> td = t.getTestDataProperties();
		PropertyFileUtil propUtil = new PropertyFileUtil("config");
		String regiondatabase = td.get(propUtil.getString("region") + ".env");
		int counter =50;
		Boolean isProcessing = true;
		while (isProcessing && counter >= 0 ) {
			String proceesque = database.executeSQLQuery(regiondatabase, query);
			System.out.println(proceesque);
			if (proceesque == null || proceesque.isEmpty()) {
				break;
			}
			TimeUnit.SECONDS.sleep(15);
			counter-- ;
		}
		if(counter <0){
			throw new RuntimeException("Process Que Verification is failed due to some other reasons");
		}
		System.out.println("Job done getting out!!!!");
	}
	
	
	
		

	// This Method return Current Date
	public static String getTodaysDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	// public static DateFormat dateformat = new
	// SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
	
	// This method return Capturing screenshot
	public static String captureScreenshot(WebDriver driver, String screenshotName) {

		String datename = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String destination = "F:\\IZMO FrameWork\\com.r1v2.com\\ScreenShots\\PageBuilder\\" + screenshotName + datename
				+ ".png";

		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshots" + e.getMessage());
		}

		return destination;
	}
	
	
	
	// This method return Capturing screenshot
	public static String captureScreenshots(WebDriver driver, String Destination, String screenshotName) {
		String path = System.getProperty("user.dir");
		String ScreenshotsPath = path + "\\ScreenShots\\" + Destination + "\\";
		String datename = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = ScreenshotsPath + screenshotName + datename + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshots" + e.getMessage());
		}
		return destination;
	}
	
	
}
