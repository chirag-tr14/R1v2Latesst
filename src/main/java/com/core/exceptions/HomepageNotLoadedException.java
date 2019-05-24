package com.core.exceptions;

/**
 * Name : HomepageNotLoadedException
 * 
 * Description : Constructs an new Runtime Exception with HomepageNotLoadedException
 * 
 * 
 *
 * Version : 1.0
 */
@SuppressWarnings("serial")
public class HomepageNotLoadedException extends RuntimeException {

    public HomepageNotLoadedException() {
        super();
    }

    public HomepageNotLoadedException(String s) {
        super(s);
    }
}
