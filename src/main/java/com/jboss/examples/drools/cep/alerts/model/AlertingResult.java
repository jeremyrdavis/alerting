package com.jboss.examples.drools.cep.alerts.model;

/**
 * For use in testing objects.
 * 
 * @author jeremy
 *
 */
public class AlertingResult {
	
	AlertingResultCode code;
	
	public AlertingResult(AlertingResultCode codeToSet){
		this.code = codeToSet;
	}
	
	public enum AlertingResultCode{
		CORRELATED, EXISTING_CORRELATED, NEW;
	}

	public AlertingResultCode getCode() {
		return code;
	}

	public void setCode(AlertingResultCode code) {
		this.code = code;
	}
}
