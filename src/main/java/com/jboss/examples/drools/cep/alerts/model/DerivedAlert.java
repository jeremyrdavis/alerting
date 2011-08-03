package com.jboss.examples.drools.cep.alerts.model;

import java.util.List;


public class DerivedAlert {
	
	String atomicAlertIds;

	List<String> deviceNames;
	
	List<String> linkIds;
	
	List<String> hubHostIds;

	public DerivedAlert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DerivedAlert(List<String> deviceNames, List<String> linkIds,
			List<String> hubHostIds) {
		super();
		this.deviceNames = deviceNames;
		this.linkIds = linkIds;
		this.hubHostIds = hubHostIds;
	}
	
	public void addAlert( Alert a ){
		System.out.println("Foo");
	}
	//--------------------------------------------------------------------------

	public List<String> getDeviceNames() {
		return deviceNames;
	}

	public void setDeviceNames(List<String> deviceNames) {
		this.deviceNames = deviceNames;
	}

	public List<String> getLinkIds() {
		return linkIds;
	}

	public void setLinkIds(List<String> linkIds) {
		this.linkIds = linkIds;
	}

	public List<String> getHubHostIds() {
		return hubHostIds;
	}

	public void setHubHostIds(List<String> hubHostIds) {
		this.hubHostIds = hubHostIds;
	}

	public String getAtomicAlertIds() {
		return atomicAlertIds;
	}

	public void setAtomicAlertIds(String atomicAlertIds) {
		this.atomicAlertIds = atomicAlertIds;
	}
	
	
	
}
