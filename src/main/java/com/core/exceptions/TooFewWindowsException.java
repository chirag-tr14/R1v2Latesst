package com.core.exceptions;

/**
 * Name : TooFewWindowsException
 * 
 * Description : Constructs an new Runtime Exception with TooFewWindowsException
 * 
 * 
 *
 * Version : 1.0
 */
@SuppressWarnings("serial")
public class TooFewWindowsException extends RuntimeException {

    public TooFewWindowsException() {
        super();
    }

    public TooFewWindowsException(String s) {
        super(s);
    }
}
