package com.jboss.examples.drools.cep.alerts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;

import com.jboss.examples.drools.cep.alerts.model.Attribute;
import com.jboss.examples.drools.cep.alerts.model.Equipment;
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
	
	@Test
	public void lookupAllAttributes(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Attribute result = (Attribute) session.get(Attribute.class, 1);
        assertNotNull("Link with id #1 should not be null", result);
        assertEquals("equipmentId should be '1234'", 1234, result.getEquipmentId());
	}

	@Test
	public void lookupAllEquipment(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Equipment result = (Equipment) session.get(Equipment.class, 1);
        assertNotNull("Equipment with id #1 should not be null", result);
        assertEquals("deviceName should be 'DEVICE1'", "DEVICE1", result.getDeviceName());
	}
}
