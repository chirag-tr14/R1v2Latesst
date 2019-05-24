package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Boni {

	
		@Test
		 public void login(){
			WebDriverManager.chromedriver().setup();
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver= new ChromeDriver();
			driver.get("http://citroen-poitiers.fr/");
			
			
		}
	}

	

