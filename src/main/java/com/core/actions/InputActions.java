package com.core.actions;


import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;



/**
 * 
 * Name : InputActions
 * 
 * Description : An interface to be implemented when you are handling input objects like keyboard and mouse.
 * 

 
 */ 

public interface InputActions {
	
	/**
	 * Returns mouse instanse.
	 */
	public Mouse getMouseObject() ;
	
	/**
	 * Returns keyboard instanse.
	 */
	public Keyboard getKeyboardObject() ;
}
