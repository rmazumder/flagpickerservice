package com.example.hcl.flagpicker.exception;

/**
 * A DataNotFoundException is thrown to indicate that data was expected but not found.
 *
 */
@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException{

	/**
     * Create a new <code>DataNotFoundException</code> with a message text.
     * 
     * @param message
     *            Message text as String
     */
	public DataNotFoundException(String message){
		super(message);
	}
	
	 /**
     * Create a new <code>DataNotFoundException</code> with a message text and the root exception.
     * 
     * @param message
     *            Message text as String
     * @param cause
     *            The root exception
     */
	public DataNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
