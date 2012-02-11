package com.jboss.examples.drools.cep.alerts;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the changes that Edson made.
 * 
 * @author jeremy
 *
 */
public class AlertingTests extends BaseAlertTest{

	@Before
	public void setUp() {
		drlFiles = new String[] { "AlertingRules.drl" };
	}
	
	@Test
	public void testChanges(){
		
	}
}
