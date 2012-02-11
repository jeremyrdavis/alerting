package com.jboss.examples.drools.cep.alerts;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.jboss.examples.drools.cep.alerts.services.AlertRuleFlowTest;
import com.jboss.examples.drools.cep.alerts.services.DataServiceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses( { NewSystemAlertTest.class, FlowTests.class, AlertRuleFlowTest.class, DataServiceTests.class, HibernateTests.class })
public class AllTests {

	
	@org.junit.Test
	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		//$JUnit-END$
		return suite;
	}

}
