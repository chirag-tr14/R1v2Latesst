package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

public class HeadlessFirsfox {

	@Test
	 public void login(){
		FirefoxBinary binary=new FirefoxBinary();
		binary.addCommandLineOptions("Headless");
		
		System.setProperty("webdriver.gecko.driver", "F:\\TNR\\Driver\\geckodriver.exe");
		FirefoxOptions fo= new FirefoxOptions();
		fo.setBinary(binary);
		
		WebDriver driver= new FirefoxDriver(fo);
		driver.get("http://citroen-poitiers.fr/");
		System.out.println(driver.getTitle());
		
				
		
	}
}
