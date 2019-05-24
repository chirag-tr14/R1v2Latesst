package com.core.actionhandlers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;


import com.core.actions.InputActions;
/**
 * Name : InputActionsHandler
 * 
 * Description : This class is an implementer for InputActions.
 * 
 * 
 * 
 * Version : 1.0
 * 
 **/
public class InputActionsHandler implements InputActions{
	
	public static final Logger logger = LogManager.getLogger(InputActionsHandler.class);
	private WebDriver driver;
	
	public InputActionsHandler(WebDriver driver){
		this.driver=driver;
	}
	
	public Mouse getMouseObject() {
         return ((HasInputDevices) driver).getMouse();
     }

     public Keyboard getKeyboardObject() {
         return ((HasInputDevices) driver).getKeyboard();
     }
     
     
     
}

