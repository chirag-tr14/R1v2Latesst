package practise;



import static com.r1v2.common.GlobalStaticInfo.PAGEBUILDER_DEPARTMENT_DROPDOWN_LIST;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class DropDown {
public static void main(String[] args) {
	
	System.setProperty("webdriver.chrome.driver", "F:\\TNR\\Driver\\chromedriver.exe");
	WebDriver driver= new ChromeDriver();
	driver.get("https://www.honda-brussels.be/contact-fr-be.htm");
	
	WebElement drop=driver.findElement(By.xpath("//select[@id='dept']"));
	//drop.click();
	
	Select sel=new Select(drop);
	List<WebElement> list=sel.getOptions();
	
	int n=list.size();
	System.out.println(n);
	sel.selectByIndex(n-2);//This one will  select last but second value
	
	//To Display All Values and if we know the value and given that value in if condition and click that
	//Value
	
	for(WebElement option : list){
		System.out.println(option.getText());
		if(option.getText().equalsIgnoreCase("Fleet")){
			option.click();
		}
      
        }
    }
}
	
		
	
	
	
	
	
	

	

