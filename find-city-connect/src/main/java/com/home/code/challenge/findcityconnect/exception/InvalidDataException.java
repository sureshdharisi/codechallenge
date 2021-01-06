package com.home.code.challenge.findcityconnect.exception;

public class InvalidDataException extends RuntimeException{

	private String message;
	
	private String errorCode;

	public InvalidDataException(String errorCode,String message) {
		super(message);
		this.message = message;
		this.errorCode=errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
	
}
