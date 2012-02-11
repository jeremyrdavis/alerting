package com.jboss.examples.drools.cep.alerts.services;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.drools.FactHandle;
import org.drools.command.assertion.AssertEquals;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.BaseAlertTest;
import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.AlertStatus;
import com.jboss.examples.drools.cep.alerts.model.AlertingResult;
import com.jboss.examples.drools.cep.alerts.model.AlertingResult.AlertingResultCode;
import com.jboss.examples.drools.cep.alerts.model.DerivedAlert;
import com.jboss.examples.drools.cep.alerts.model.SystemAlert;

public class AlertRuleFlowTest extends BaseAlertTest {

	@Before
	public void setUp() {
		drlFiles = new String[] { "AlertFlowRules.drl" };
		flowFiles = new String[] { "AlertRuleFlow.rf" };
	}

	@Test
	public void testValidationStep() throws Exception {
		System.out.println("*************************************");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		ksession.insert(new SystemAlert("25", Calendar.getInstance().getTime(),
				"deviceName", "interfaceName", AlertStatus.ACTIVE));
		ksession.startProcess("Alert Rule Flow");
		ksession.fireAllRules();
	}

	@Test
	public void testFindingARelatedAlert() throws Exception {
		System.out.println("*************************************");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		AlertingResult result = new AlertingResult(AlertingResultCode.NEW); 
		ksession.insert(result);
		ksession.insert(new SystemAlert("25", Calendar.getInstance().getTime(),
				"DEVICE1", "interfaceName", AlertStatus.ACTIVE));
		ksession.insert(new SystemAlert("26", Calendar.getInstance().getTime(),
				"DEVICE1", "interfaceName", AlertStatus.ACTIVE));
		ksession.startProcess("Alert Rule Flow");
		ksession.fireAllRules();
		assertEquals("AlertingResultCode should be EXISTING_CORRELATION", AlertingResultCode.EXISTING_CORRELATED, result.getCode());
	}

	@Test
	/**
	 * Verifies that a SystemAlert for which there is already a correlating alert will match to the correlating DerivedAlert.
	 * 
	 * @see Alert, SystemAlert, DerivedAlert
	 */
	public void testFindingAnExistingDerivedAlert() throws Exception {
		System.out.println("*************************************");

		StatefulKnowledgeSession ksession = createKnowledgeSession();

		// create an alert
		SystemAlert alert = new SystemAlert("25", Calendar.getInstance()
				.getTime(), "DEVICE1", "interfaceName", AlertStatus.ACTIVE);

		// create the derived alert
		DerivedAlert derivedAlert = new DerivedAlert();
		derivedAlert.addAlert(new Alert(alert));

		// create a second one for the same device
		SystemAlert secondAlert = new SystemAlert("25", Calendar.getInstance()
				.getTime(), "DEVICE1", "interfaceName", AlertStatus.ACTIVE);

		ksession.insert(derivedAlert);
		ksession.insert(secondAlert);

		ksession.startProcess("Alert Rule Flow");
		ksession.fireAllRules();
	}
	
	@Test
	/**
	 * This test verifies that a new SystemAlert will not hit unrelated DerivedAlerts.
	 */
	public void testExistingDerivedAlertsAreIgnored() throws Exception{
		System.out.println("*************************************");

		StatefulKnowledgeSession ksession = createKnowledgeSession();

		// create an alert
		SystemAlert alert = new SystemAlert("25", Calendar.getInstance()
				.getTime(), "DEVICE1", "interfaceName", AlertStatus.ACTIVE);

		// create the derived alert
		DerivedAlert derivedAlert = new DerivedAlert();
		derivedAlert.addAlert(new Alert(alert));

		// create a second one for the same device
		SystemAlert secondAlert = new SystemAlert("26", Calendar.getInstance()
				.getTime(), "DEVICE2", "interfaceName", AlertStatus.ACTIVE);

		ksession.insert(derivedAlert);
		ksession.insert(secondAlert);

		ksession.startProcess("Alert Rule Flow");
		ksession.fireAllRules();
	}

}
