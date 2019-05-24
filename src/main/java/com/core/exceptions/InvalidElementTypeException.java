package com.core.exceptions;

/**
 * Name : InvalidElementTypeException
 * 
 * Description : Constructs an new Runtime Exception with InvalidElementTypeException
 * 
 * 
 *
 * Version : 1.0
 */
@SuppressWarnings("serial")
public class InvalidElementTypeException extends RuntimeException {

    public InvalidElementTypeException() {
        super();
    }

    public InvalidElementTypeException(String s) {
        super(s);
    }
}