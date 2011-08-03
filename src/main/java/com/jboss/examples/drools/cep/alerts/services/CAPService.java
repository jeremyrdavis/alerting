package com.jboss.examples.drools.cep.alerts.services;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.DerivedAlert;

public class CAPService {
	
	public static void sendCAPAlert(Alert alert){
		System.out.println("CAPAlert");
	}

	public static void sendCAPAlert(DerivedAlert alert){
		System.out.println("CAPAlert");
	}
}
