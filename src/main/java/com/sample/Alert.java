package com.sample;

import java.util.Date;

public class Alert {

	protected String id;
	
	protected Date time;
	
	protected String deviceName;
	
	protected String interfaceName;
	
	protected AlertStatus status;
	
	protected String upstreamLink;
	
	protected String downstreamLink;

	public Alert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alert(String id, Date time, String deviceName, String interfaceName,
			AlertStatus status, String upstreamLink, String downstreamLink) {
		super();
		this.id = id;
		this.time = time;
		this.deviceName = deviceName;
		this.interfaceName = interfaceName;
		this.status = status;
		this.upstreamLink = upstreamLink;
		this.downstreamLink = downstreamLink;
	}
	
	/**
	 * Constructor for use with a SystemAlert.
	 * 
	 * @param systemAlert
	 */
	public Alert(SystemAlert systemAlert){
		this.time = systemAlert.getTime();
		this.deviceName = systemAlert.getDeviceName();
		this.interfaceName = systemAlert.getInterfaceName();
		this.status = systemAlert.getStatus();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public AlertStatus getStatus() {
		return status;
	}

	public void setStatus(AlertStatus status) {
		this.status = status;
	}

	public String getUpstreamLink() {
		return upstreamLink;
	}

	public void setUpstreamLink(String upstreamLink) {
		this.upstreamLink = upstreamLink;
	}

	public String getDownstreamLink() {
		return downstreamLink;
	}

	public void setDownstreamLink(String downstreamLink) {
		this.downstreamLink = downstreamLink;
	}
	
	
}
