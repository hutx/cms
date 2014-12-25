package com.cms.system.util;

public class BusiException extends Exception{
	
	private static final long serialVersionUID = 9064185607395035608L;
	public static final String ERR_DBERROR = "200006";
	public BusiException(String errcode, String message) {
	        super(message);
	    }

    public BusiException(String errcode, String message, Throwable cause) {
        super(message, cause);
    }
}
