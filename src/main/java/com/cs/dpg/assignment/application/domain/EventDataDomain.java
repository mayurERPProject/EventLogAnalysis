package com.cs.dpg.assignment.application.domain;

import java.io.Serializable;

public class EventDataDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -114974288529305303L;

	private String eventId;
	private Long duration;
	private String type;
	private String host;
	private boolean alert;
	private long timestamp;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	@Override
	public String toString() {
		return "EventDataDomain [eventId=" + eventId + ", duration=" + duration + ", type=" + type + ", host=" + host
				+ ", alert=" + alert + "]";
	}
	
}
