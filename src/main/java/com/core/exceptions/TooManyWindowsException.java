package com.core.exceptions;

/**
 * Name : TooManyWindowsException
 * 
 * Description : Constructs an new Runtime Exception with TooManyWindowsException
 * 
 * 
 *
 * Version : 1.0
 */
@SuppressWarnings("serial")
public class TooManyWindowsException extends RuntimeException {

    public TooManyWindowsException() {
        super();
    }

    public TooManyWindowsException(String s) {
        super(s);
    }
}
