package com.jboss.examples.drools.cep.alerts.services;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.DerivedAlert;

public class CAPService {
	
	public static void sendCAPAlert(Alert alert){
		System.out.println("CAPAlert Single");
	}

	public static void sendMultipleCAPAlert(DerivedAlert alert){
		System.out.println("CAPAlert Multiple");
	}
}
