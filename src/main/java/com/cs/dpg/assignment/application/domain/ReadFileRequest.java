package com.cs.dpg.assignment.application.domain;

import java.sql.Connection;


public class ReadFileRequest {

	private String jobId;
	private Connection connection;
	private EventDataDomain eventDataDomain;
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public EventDataDomain getEventDataDomain() {
		return eventDataDomain;
	}

	public void setEventDataDomain(EventDataDomain eventDataDomain) {
		this.eventDataDomain = eventDataDomain;
	}
	
}
