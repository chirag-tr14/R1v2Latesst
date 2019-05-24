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
	
	
	
	public static void cleanupData(String query) throws InterruptedException {

		DataBase database = new DataBase();
		BaseTest t = new BaseTest();
		Map<String, String> td = t.getTestDataProperties();
		PropertyFileUtil propUtil = new PropertyFileUtil("config");
		String regiondatabase = td.get(propUtil.getString("region") + ".env");
		//database.executeSQLQuery(regiondatabase, query);
		database.execute(regiondatabase, query);
	          
	   }
	

	// This Method return Current Date
	public static String getTodaysDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	// public static DateFormat dateformat = new
	// SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
	
	
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
	
	
	
	
	
	
	public String generateRandomEmailAddress(String domain) {
		String emailAddress = "";
		// Generate random email address
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		while (emailAddress.length() < 5) {
			int character = (int) (Math.random() * 26);
			emailAddress += alphabet.substring(character, character + 1);
		}
		emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
		emailAddress += "@" + domain;
		return emailAddress;
	}

	public static String stripNonDigits(final CharSequence input) {
		final StringBuilder sb = new StringBuilder(input.length());
		for (int i = 0; i < input.length(); i++) {
			final char c = input.charAt(i);
			if (c > 47 && c < 58) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String getRandomString(int len) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getRandomAlphabet(int len) {
		final String AB = "qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

}
