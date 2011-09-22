package com.jboss.examples.drools.cep.alerts.errors;

/**
 * Represents the business supplied error codes.
 * 
 * @author jeremy
 *
 */
public enum AlertValidationErrorCodes {
	
	MISSING_DEVICE("Device is missing", 10), INVALID_DEVICE("Device is invalid", 11);
	
	public String message;
	
	public int errorCode;
	
	private AlertValidationErrorCodes(String msgToSet, int codeToSet){
		this.message = msgToSet;
		this.errorCode = codeToSet;
	}

}
