package com.jboss.examples.drools.cep.alerts.services;

import com.jboss.examples.drools.cep.alerts.model.Equipment;
import com.jboss.examples.drools.cep.alerts.model.Link;

public class DataService {
	
	public static Link lookupDownstreamLink( String deviceName ){
		return new Link(1234, "upstreamDeviceName", "downstreamDeviceName",
				"upstreamPort", "downstreamPort");
	}

	public static Link lookupUpstreamLink( String deviceName ){
		return new Link(1234, "upstreamDeviceName", "downstreamDeviceName",
				"upstreamPort", "downstreamPort");
	}
	
	public static Equipment lookupEquipment( String deviceName ){
		return new Equipment(1, 1234, "DEVICE2");
	}
}
