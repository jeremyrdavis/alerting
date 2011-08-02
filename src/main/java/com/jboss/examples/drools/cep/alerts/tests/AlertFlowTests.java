package com.jboss.examples.drools.cep.alerts.tests;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.sample.Alert;

public class AlertFlowTests extends BaseAlertTest{

	@Before
	public void setUp(){
		drlFiles = new String[]{"Device2Rules.drl"};
		flowFiles = new String[]{"AlertFlow.rf"};
	}

	@Test
	public void testValidationStep() throws Exception{
		System.out.println("*************************************");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		ksession.insert(new Alert());
		ksession.startProcess("Device2");
		ksession.fireAllRules();
	}

}
