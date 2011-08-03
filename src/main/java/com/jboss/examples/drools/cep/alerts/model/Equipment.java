package com.jboss.examples.drools.cep.alerts.model;

import java.io.Serializable;

public class Equipment implements Serializable{

	private static final long serialVersionUID = 7453763859207685282L;

	private int id;
	private int equipmentId;
	private String deviceName;
	
	public Equipment() {
		super();
	}
	
	

	public Equipment(int id, int equipmentId, String deviceName) {
		super();
		this.id = id;
		this.equipmentId = equipmentId;
		this.deviceName = deviceName;
	}

	//--------------------------------------------------------------------------
	//	generated
	//--------------------------------------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
