/**
 * 
 */
package com.cs.dpg.assignment.application.domain;

import java.io.Serializable;

/**
 * @author mayur
 *
 */
public class EventData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7692257654965218123L;
	
	private String id;
	private String state;
	private long timestamp;
	private String type;
	private String host;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "EventData [id=" + id + ", state=" + state + ", timestamp=" + timestamp + ", type=" + type + ", host="
				+ host + "]";
	}

}
