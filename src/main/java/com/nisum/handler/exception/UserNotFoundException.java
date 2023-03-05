package com.nisum.handler.exception;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public UserNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
