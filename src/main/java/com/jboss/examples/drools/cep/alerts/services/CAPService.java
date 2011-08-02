package com.jboss.examples.drools.cep.alerts.services;

import com.sample.Alert;
import com.sample.DerivedAlert;

public class CAPService {
	
	public static void sendCAPAlert(Alert alert){
		System.out.println("CAPAlert");
	}

	public static void sendCAPAlert(DerivedAlert alert){
		System.out.println("CAPAlert");
	}
}
