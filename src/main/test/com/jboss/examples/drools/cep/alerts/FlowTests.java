package com.jboss.examples.drools.cep.alerts;

import static org.junit.Assert.*;

import org.drools.event.DebugProcessEventListener;
import org.drools.event.process.ProcessEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.jboss.examples.drools.cep.alerts.model.Alert;
import com.jboss.examples.drools.cep.alerts.model.DerivedAlert;
import com.jboss.examples.drools.cep.eventListeners.TrackingProcessEventListener;

public class FlowTests extends BaseAlertTest{

	StatefulKnowledgeSession ksession;
	
	@Before
	public void setUp(){
		
		logger = LoggerFactory.getLogger(FlowTests.class);
		
		drlFiles = new String[]{"FlowRules.drl"};
		flowFiles = new String[]{"AlertFlow.rf"};
		
		try {
			ksession = createKnowledgeSession();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		
		logger.debug("setUp complete");
	}

	@Test
	public void testValidationStep() throws Exception{
		logger.debug("Test initial step");
		WorkingMemoryEventListener listener = new WorkingMemoryEventListener() {
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				System.out.println(arg0);
			}

			@Override
			public void objectRetracted(ObjectRetractedEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
		ksession.addEventListener(listener);
		TrackingProcessEventListener trackingProcessEventListener = new TrackingProcessEventListener();
		ksession.addEventListener(trackingProcessEventListener);
		ksession.insert(new Alert());
		ksession.startProcess("Alert Flow");
		ksession.fireAllRules();
		assertTrue(	trackingProcessEventListener.isRuleFired("Validate DeviceName") );
		assertTrue(	trackingProcessEventListener.isRuleFired("AlertValidationError Exists") );
		
	}
	
	@Test
	public void testNewAlertCorrespondsToExistingDerivedAlert() throws Exception{
		StatefulKnowledgeSession ksession = createKnowledgeSession();
		
		Alert firstAlert = new Alert();
		firstAlert.setDeviceName("DEVICE2");
		Alert secondAlert = new Alert();
		secondAlert.setDeviceName("DEVICE2");
		
		DerivedAlert derivedAlert = new DerivedAlert();
		derivedAlert.addAlert(firstAlert);
		ksession.insert(derivedAlert);
		
		ksession.insert(secondAlert);
		ksession.startProcess("Alert Flow");
		ksession.fireAllRules();
	}

}
