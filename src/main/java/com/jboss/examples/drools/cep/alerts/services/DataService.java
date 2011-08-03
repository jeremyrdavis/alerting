package com.jboss.examples.drools.cep.alerts.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.jboss.examples.drools.cep.alerts.model.Equipment;
import com.jboss.examples.drools.cep.alerts.model.HibernateUtil;
import com.jboss.examples.drools.cep.alerts.model.Link;

public class DataService {
	
	public static Link lookupDownstreamLink( String deviceName ){
		return lookupLink(LinkType.DOWNSTREAM, deviceName);
	}

	public static Link lookupUpstreamLink( String deviceName ){
		return lookupLink(LinkType.UPSTREAM, deviceName);
	}
	
	private static Link lookupLink(LinkType linkType, String deviceNameToLookup){
		Session session = getSession();
		session.beginTransaction();
		List<Link> result = session.createCriteria(Link.class).add( Restrictions.eq(linkType.value, deviceNameToLookup) ).list();
		if( result != null && result.size() >= 1){
			return result.get(0);
		}
		return null;
	}
	
	public static Equipment lookupEquipment( String deviceName ){
		return new Equipment(1, 1234, "DEVICE2");
	}
	
	private static Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	enum LinkType{
		DOWNSTREAM("downstreamDevice"), UPSTREAM("upstreamDevice");
		
		protected String value;
		
		private LinkType(String arg){
			this.value = arg;
		}
	}
}

