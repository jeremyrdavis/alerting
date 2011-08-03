package com.jboss.examples.drools.cep.alerts.services.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Link;
import com.jboss.examples.drools.cep.alerts.services.DataService;

public class DataServiceTests {

	DataService svc;

	@Test
	public void testLookupDownstreamLink() throws Exception{
		Link result = svc.lookupDownstreamLink("DEVICE1A");
		assertNotNull("Should have a result", result);
		verifyLink(result);
	}

	@Test
	public void testLookupUpstreamLink() throws Exception{
		Link result = svc.lookupUpstreamLink("DEVICE1");
		assertNotNull("Should have a result", result);
		verifyLink(result);
	}
	
	private void verifyLink(Link linkToVerify){
		assertNotNull("Should have an upstreamPort", linkToVerify.getUpstreamPort());
		assertNotNull("Should have a downstreamPort", linkToVerify.getDownstreamPort());
		assertNotNull("Should have a downstreamDevice", linkToVerify.getDownstreamDevice());
		assertNotNull("Should have a upstreamDevice", linkToVerify.getUpstreamDevice());
	}
}
