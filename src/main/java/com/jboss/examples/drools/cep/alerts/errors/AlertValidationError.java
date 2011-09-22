package com.jboss.examples.drools.cep.alerts.errors;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.jboss.examples.drools.cep.alerts.model.Alert;

public class AlertValidationError implements Serializable {
	
	private static final long serialVersionUID = 1371231092895574194L;

	/**
	 * The Alert that contains an error
	 */
	private Alert alert;
	
	/**
	 * Message detailing the type of error.
	 */
	private String message;

	public AlertValidationError(Alert alert, String message) {
		super();
		this.alert = alert;
		this.message = message;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(98587969, 810426655).append(message).append(alert).toHashCode();
	}

	@Override
	public String toString(){
		return new ToStringBuilder(this).append("message", message).append("alert", alert).toString();
	}

	//--------------------------------------------------------------------------
	// generated
	//--------------------------------------------------------------------------
	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
