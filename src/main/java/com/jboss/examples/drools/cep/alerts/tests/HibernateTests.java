package com.jboss.examples.drools.cep.alerts.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.HibernateUtil;
import com.jboss.examples.drools.cep.alerts.model.Link;

public class HibernateTests {

	@Test
	public void lookupAllLinks(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Link result = (Link) session.get(Link.class, 1);
        assertNotNull("Link with id #1 should not be null", result);
        assertEquals("upstreamDevice should be 'DEVICE1'", "DEVICE1", result.getUpstreamDevice());
	}
	
}
