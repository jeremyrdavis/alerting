package com.sample;

import java.util.Date;

public class SystemAlert {

	protected String id;
	
	protected Date time;
	
	protected String deviceName;
	
	protected String interfaceName;
	
	protected AlertStatus status;

	public SystemAlert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemAlert(String id, Date time, String deviceName,
			String interfaceName, AlertStatus status) {
		super();
		this.id = id;
		this.time = time;
		this.deviceName = deviceName;
		this.interfaceName = interfaceName;
		this.status = status;
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
}
