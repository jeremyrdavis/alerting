package com.jboss.examples.drools.cep.alerts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.AlertStatus;
import com.jboss.examples.drools.cep.alerts.model.DerivedAlert;
import com.jboss.examples.drools.cep.alerts.model.SystemAlert;
import com.sample.Alarm;


public class NewSystemAlertTest extends BaseAlertTest {
	
	@Before
	public void setUp(){
		drlFiles = new String[]{"Sample.drl"};
	}
	
	@Test
	public void testCreatingNewAlertFromSystemAlert() throws Exception{
		System.out.println("*************************************");
		System.out.println("testCreatingNewAlertFromSystemAlert");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		
		SystemAlert systemAlert = new SystemAlert("25", Calendar.getInstance().getTime(), "DEVICE1",
				"DEVICE1A", AlertStatus.ACTIVE);
		ksession.insert(systemAlert);
		ksession.fireAllRules();
	}

	@Test
	public void testDeviceNameCorrelationFromSystemAlert() throws Exception{
		System.out.println("*************************************");
		System.out.println("testDeviceNameCorrelationFromSystemAlert");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		
		DerivedAlert derivedAlert = new DerivedAlert();
		List<String> deviceNames = new ArrayList<String>();
		deviceNames.add("deviceName");
		derivedAlert.setDeviceNames(deviceNames);
		SystemAlert systemAlert = new SystemAlert("25", Calendar.getInstance().getTime(), "deviceName",
				"interfaceName", AlertStatus.ACTIVE);
		ksession.insert(derivedAlert);
		ksession.insert(systemAlert);
		ksession.fireAllRules();
	}
	
	@Test
	public void testSystemAlertCorrelatesWithExistingAlert() throws Exception{
		System.out.println("*************************************");
		System.out.println("testSystemAlertCorrelatesWithExistingAlert");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		
		Alert alert = new Alert();
		alert.setDeviceName("DEVICE1A");
		
		SystemAlert systemAlert = new SystemAlert("25", Calendar.getInstance().getTime(), "DEVICE1A",
				"", AlertStatus.ACTIVE);
		
		ksession.insert(alert);
		ksession.insert(systemAlert);
		ksession.fireAllRules();
	}
	
	@Test
	public void testCAPAlert() throws Exception{
		System.out.println("*************************************");
		System.out.println("testCAPAlert");
		Alert alert = new Alert();
		alert.setDeviceName("DEVICE1A");
		alert.setTime(Calendar.getInstance().getTime());
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		ksession.insert(alert);
        Thread.currentThread().sleep( 7000 );
		ksession.insert(new Alarm(Calendar.getInstance().getTime()));
		ksession.fireAllRules();
	}
}
