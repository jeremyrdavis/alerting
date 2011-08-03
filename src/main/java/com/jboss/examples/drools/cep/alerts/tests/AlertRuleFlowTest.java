package com.jboss.examples.drools.cep.alerts.tests;

import java.util.Calendar;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.AlertStatus;
import com.jboss.examples.drools.cep.alerts.model.SystemAlert;

public class AlertRuleFlowTest extends BaseAlertTest {

	@Before
	public void setUp(){
		drlFiles = new String[]{"AlertFlowRules.drl"};
		flowFiles = new String[]{"AlertRuleFlow.rf"};
	}

	@Test
	public void testValidationStep() throws Exception{
		System.out.println("*************************************");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		ksession.insert(new SystemAlert("25", Calendar.getInstance().getTime(), "deviceName",
				"interfaceName", AlertStatus.ACTIVE));
		ksession.startProcess("Alert Rule Flow");
		ksession.fireAllRules();
	}

}
