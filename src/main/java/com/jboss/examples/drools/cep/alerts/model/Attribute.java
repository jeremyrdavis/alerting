package com.jboss.examples.drools.cep.alerts.model;

// Generated Jul 23, 2011 9:10:48 PM by Hibernate Tools 3.4.0.CR1

public class Attribute implements java.io.Serializable {

	private static final long serialVersionUID = 8234080434403233599L;

	private int id;
	private int equipmentId;
	private String hubHostName;
	private int rack;
	private int shelf;
	
	public Attribute() {
	}

	
	public Attribute(int id, int equipmentId, String hubHostName, int rack,
			int shelf) {
		super();
		this.id = id;
		this.equipmentId = equipmentId;
		this.hubHostName = hubHostName;
		this.rack = rack;
		this.shelf = shelf;
	}


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

	public String getHubHostName() {
		return hubHostName;
	}

	public void setHubHostName(String hubHostName) {
		this.hubHostName = hubHostName;
	}

	public int getRack() {
		return rack;
	}

	public void setRack(int rack) {
		this.rack = rack;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

}
