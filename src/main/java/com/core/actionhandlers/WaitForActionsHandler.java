package com.core.actionhandlers;

import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.actions.WaitForActions;


/**
 * Name : WaitForActionsHandler
 * 
 * Description : This class is an implementer for WaitForActions.
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
public class WaitForActionsHandler implements WaitForActions{


	private WebDriver driver;
	final int timeout;
	private int defaultTimeout = Integer.parseInt(ResourceBundle.getBundle("config").getString("defaultTimeOut"));
	public static final Logger logger = LogManager.getLogger(WaitForActionsHandler.class);
	
	public WaitForActionsHandler(WebDriver driver, int passedTimeout){
		this.driver=driver;
		timeout = passedTimeout;
	}
	
	public WaitForActionsHandler(WebDriver driver){
		this.driver=driver;
		timeout = defaultTimeout;
	}

	public void setDefaultTimeout(int timeout) {
		defaultTimeout = timeout;
	}

	public int getDefaultTimeout(int timeout) {
		return defaultTimeout;
	}   

	public ForWebElements untilWebElement(By elementLocator) {
		return new WaitForWebElements(elementLocator);
	}

	private class WaitForWebElements implements ForWebElements {
		final By elementLocator;

		public WaitForWebElements(By passedElement) {
			elementLocator = passedElement;
		}

		public void waitForStaleElement(){
			new WebDriverWait(driver,timeout)
			.ignoring(StaleElementReferenceException.class)
			.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver webDriver) {
					WebElement element = webDriver.findElement(elementLocator);
					return element != null && element.isDisplayed();
				}
			});
		}
		
		public void exists() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() > 0);
				}
			});
		}

		public void doesNotExist() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() < 1);
				}
			});
		}

		public void instancesAreMoreThan(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() > instances);
				}
			});
		}

		public void instancesAreLessThan(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() < instances);
				}
			});
		}

		public void instancesEqual(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() == instances);
				}
			});
		}

		public void instancesDoNotEqual(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (driver.findElements(elementLocator).size() != instances);
				}
			});
		}

		public void existsAfterRefreshingPage() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return (driver.findElements(elementLocator).size() > 0);
				}
			});
		}

		public void doesNotExistAfterRefreshingPage() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return (driver.findElements(elementLocator).size() < 1);
				}
			});
		}

		public void instancesAreMoreThanAfterRefreshingPage(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return (driver.findElements(elementLocator).size() > instances);
				}
			});
		}

		public void instancesAreLessThanAfterRefreshingPage(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return (driver.findElements(elementLocator).size() < instances);
				}
			});
		}

		public void instancesEqualAfterRefreshingPage(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return driver.findElements(elementLocator).size() == instances;
				}
			});
		}

		public void instancesDoNotEqualAfterRefreshingPage(final int instances) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.navigate().refresh();
					return driver.findElements(elementLocator).size() != instances;
				}
			});
		}

		public void textIsEqualTo(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.findElement(elementLocator).getText().equals(text);
				}
			});
		}

		public void textDoesNotEqual(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return !driver.findElement(elementLocator).getText().equals(text);
				}
			});
		}

		public void textContains(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.findElement(elementLocator).getText().contains(text);
				}
			});
		}

		public void textDoesNotContain(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return !driver.findElement(elementLocator).getText().contains(text);
				}
			});
		}

		public void titleIsEqualTo(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getTitle().equals(text);
				}
			});
		}

		public void titleDoesNotEqual(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return !driver.getTitle().equals(text);
				}
			});
		}

		public void titleContains(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getTitle().contains(text);
				}
			});
		}

		public void titleDoesNotContain(final String text) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return !driver.getTitle().contains(text);
				}
			});
		}
	}

	public ForWindows untilWindow() {
		return new WaitForWindows();
	}

	private class WaitForWindows implements ForWindows {

		public void countEquals(final int count) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getWindowHandles().size() == count;
				}
			});
		}

		public void countDoesNotEqual(final int count) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getWindowHandles().size() != count;
				}
			});
		}

		public void countIsGreaterThan(final int count) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getWindowHandles().size() > count;
				}
			});
		}

		public void countIsLessThan(final int count) {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.getWindowHandles().size() < count;
				}
			});
		}
	}

	public ForEvent untilEvent() {
		return new WaitForEvent();
	}

	private class WaitForEvent implements ForEvent {

		public void untilJQueryProcessingHasCompleted() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					boolean jQueryActive = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
					return jQueryActive;
				}
			});
		}
		public void untilJSProcessingHasCompleted() {
			new WebDriverWait(driver, timeout) {
			}.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					String status = (String)((JavascriptExecutor) driver).executeScript("return document.readyState");
					boolean jQueryActive = (Boolean) (status.equals("complete")||status.equals("loaded"));
					return jQueryActive;
				}
			});
		}
	}

}

