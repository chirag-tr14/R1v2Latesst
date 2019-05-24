package com.core.actions;


import org.openqa.selenium.By;
/**
 * Name:WaitForActions
 * 
 * Description:This interface will be implemented to handle all sort of Wait conditions 
 * 
 * Author: Rajesh
 *
 * Version:1.0
 */
public interface WaitForActions {

    ForWebElements untilWebElement(By element);

    interface ForWebElements {

    	
    	/**
    	 * Wait until new state of element present
    	 */
    	public void waitForStaleElement();
    	
    	/**
    	 * Wait until element exists
    	 */
        public void exists();

        /**
    	 * Wait until element doedn't exists
    	 */
        public void doesNotExist();

        /**
    	 * Wait until instances are more than the number given
    	 * 
    	 * @param int - no.of instances
    	 */
        public void instancesAreMoreThan(int instances);
        
        /**
    	 * Wait until instances are less than the number given
    	 * 
    	 * @param int - no.of instances
    	 * 
    	 */
        public void instancesAreLessThan(int instances);

        /**
    	 * Wait until instances are equal to the number given
    	 * 
    	 * @param int - no.of instances
    	 * 
    	 */
        public void instancesEqual(int instances);

        /**
    	 * Wait until instances are not equal the number given
    	 * 
    	 * @param int - no.of instances
    	 * 
    	 */
        public void instancesDoNotEqual(int instances);

        /**
    	 * Wait until exists after refreshing page 
    	 */
        public void existsAfterRefreshingPage();

        /**
    	 * Wait until does exists after refreshing page 
    	 */
        public void doesNotExistAfterRefreshingPage();

        
        /**
    	 * Wait until instances are more than the number given after refreshing the page
    	 * 
    	 * @param int - no.of instances
    	 */
        public void instancesAreMoreThanAfterRefreshingPage(int instances);

        /**
    	 * Wait until instances are less than the number given after refreshing the page
    	 * 
    	 * @param int - no.of instances
    	 */
        public void instancesAreLessThanAfterRefreshingPage(int instances);

        /**
    	 * Wait until instances are equal to the number given after refreshing the page
    	 * 
    	 * @param int - no.of instances
    	 */
        public void instancesEqualAfterRefreshingPage(int instances);

        /**
    	 * Wait until instances are not equal to the number given after refreshing the page
    	 * 
    	 * @param int - no.of instances
    	 */
        public void instancesDoNotEqualAfterRefreshingPage(int instances);

        /**
    	 * Wait until the given text is equal to element text
    	 * 
    	 * @param String - element text
    	 */
        public void textIsEqualTo(String text);

        /**
    	 * Wait until the given text is not equal to element text
    	 * 
    	 * @param String - element text
    	 */
        public void textDoesNotEqual(String text);

        
        /**
    	 * Wait until the given text is part of element text
    	 * 
    	 * @param String - element text
    	 */
        public void textContains(String text);

        /**
    	 * Wait until the given text is not part of element text
    	 * 
    	 * @param String - element text
    	 */        
        public void textDoesNotContain(String text);

        
        /**
    	 * Wait until the title of page is equal to given text
    	 * 
    	 * @param String - element text
    	 */
        public void titleIsEqualTo(String text);

        /**
    	 * Wait until the title of page is not equal to given text
    	 * 
    	 * @param String - element text
    	 */
        public void titleDoesNotEqual(String text);

        /**
    	 * Wait until the title of page contains given text
    	 * 
    	 * @param String - element text
    	 */
        public void titleContains(String text);

        /**
    	 * Wait until the title of page does not contains given text
    	 * 
    	 * @param String - element text
    	 */
        public void titleDoesNotContain(String text);
    }

    ForWindows untilWindow();

    interface ForWindows {

    	/**
    	 * Wait until the number of windows opened is equal to the given count
    	 * 
    	 * @param int - count
    	 */
    	public void countEquals(int count);

    	/**
    	 * Wait until the number of windows opened is not equal to the given count
    	 * 
    	 * @param int - count
    	 */
        public void countDoesNotEqual(int count);

        
        /**
    	 * Wait until the number of windows opened is greater than the given count
    	 * 
    	 * @param int - count
    	 */
        public void countIsGreaterThan(int count);

        /**
    	 * Wait until the number of windows opened is lessthan the given count
    	 * 
    	 * @param int - count
    	 */
        public void countIsLessThan(int count);
    }

    ForEvent untilEvent();

    interface ForEvent {
    	/**
    	 * Wait until JQuery processing completes
    	 */
        public void untilJQueryProcessingHasCompleted();
        /**
    	 * Wait until Javascript processing completes
    	 */
        public void untilJSProcessingHasCompleted();
    }
}
