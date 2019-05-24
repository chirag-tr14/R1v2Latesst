package practise;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenLinks {
	
	@Test
	public void brokenLinks() throws MalformedURLException, IOException, InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "F:\\TNR\\Driver\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://ui.freecrm.com/");
		//driver.findElement(By.name("email")).sendKeys("rajesh.tn30@gmail.com");
		//driver.findElement(By.name("password")).sendKeys("Rc14");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		
		
		Thread.sleep(100);
		
			
		List<WebElement> links= driver.findElements(By.tagName("a"));
				//links.addAll(driver.findElements(By.tagName("img")));
				System.out.println("Size of  actaul link--->"+links.size());
				
				
		List<WebElement> actvieLinks=new ArrayList<WebElement>();
		
		for(int i=0;i<links.size();i++){
			//System.out.println(links.get(i).getAttribute("href"));
		if(links.get(i).getAttribute("href")!=null && (!links.get(i).getAttribute("href").
				contains("javascript")));
				actvieLinks.add(links.get(i));
		}
		System.out.println("Size of  active link--->"+actvieLinks.size());
		
		for( int j=0;j<actvieLinks.size();j++){
			
		  HttpURLConnection conn=(HttpURLConnection)new URL(actvieLinks.get(j).getAttribute("href")).openConnection();
		  conn.connect();
		  String response=conn.getResponseMessage();
		  conn.disconnect();
		  System.out.println(actvieLinks.get(j).getAttribute("href"+"--->>+"+response));
		}
		}

	
	

}
