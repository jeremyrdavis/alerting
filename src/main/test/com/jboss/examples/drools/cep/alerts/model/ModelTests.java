package com.jboss.examples.drools.cep.alerts.model;

import java.util.Calendar;

import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.AlertStatus;

public class ModelTests {

	@Test
	public void testAlertToString(){
		Alert a = new Alert("123", Calendar.getInstance().getTime(), "deviceName", "interfaceName", AlertStatus.ACTIVE, null, null);
		System.out.println(a.toString());
	}
}
