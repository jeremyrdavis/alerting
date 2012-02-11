package com.jboss.examples.drools.cep.alerts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.time.SessionPseudoClock;
import org.junit.Before;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.AlertStatus;
import com.jboss.examples.drools.cep.alerts.model.SystemAlert;

public class AlertFlowTest extends BaseAlertTest {

	@Before
	public void setUp() {
		drlFiles = new String[] { "AlertFlowRules.drl" };
	}

	@Test
	public void testSingleAlert() throws Exception {
		System.out.println("Testing Single Alert");
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		SessionPseudoClock clock = ksession.getSessionClock();

		SystemAlert firstAlert = new SystemAlert();
		firstAlert.setDeviceName("DEVICE2");
		firstAlert.setStatus(AlertStatus.ACTIVE);

		ksession.insert(firstAlert);

		ksession.fireAllRules();

		clock.advanceTime(66, TimeUnit.SECONDS);

		ksession.fireAllRules();

		droolsLogger.writeToDisk();

	}

	@Test
	public void testMultipleAlert() throws Exception {
		System.out.println("Testing Multiple Alert");
		final StatefulKnowledgeSession ksession = createKnowledgeSession();
		SessionPseudoClock clock = ksession.getSessionClock();

		SystemAlert firstAlert = new SystemAlert();
		firstAlert.setDeviceName("DEVICE2");
		firstAlert.setStatus(AlertStatus.ACTIVE);
		firstAlert.setId("1");

		SystemAlert secondAlert = new SystemAlert();
		secondAlert.setDeviceName("DEVICE2");
		secondAlert.setStatus(AlertStatus.ACTIVE);
		secondAlert.setId("2");

		SystemAlert thirdAlert = new SystemAlert();
		thirdAlert.setDeviceName("DEVICE2");
		thirdAlert.setStatus(AlertStatus.ACTIVE);
		thirdAlert.setId("3");

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				ksession.fireUntilHalt();
			}

		});

		ksession.insert(firstAlert);

		clock.advanceTime(10, TimeUnit.SECONDS);
		ksession.insert(secondAlert);
		clock.advanceTime(80, TimeUnit.SECONDS);
		ksession.insert(thirdAlert);
		clock.advanceTime(80, TimeUnit.SECONDS);

		t.join();
		droolsLogger.writeToDisk();

	}
	
}
