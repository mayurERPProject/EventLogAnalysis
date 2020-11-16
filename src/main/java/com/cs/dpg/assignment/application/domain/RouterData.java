package com.cs.dpg.assignment.application.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import akka.actor.ActorRef;

public class RouterData {

	private Map<String, String> jobStatus = new HashMap<String, String>();
	private AtomicInteger jobCompletionCount = new AtomicInteger(0);
	private AtomicInteger jobInprogressCount = new AtomicInteger(0);
	private EventDataDomain eventDataDomain = new EventDataDomain();

	private ActorRef sender;

	public EventDataDomain getEventDataDomain() {
		return eventDataDomain;
	}

	public void setEventDataDomain(EventDataDomain eventDataDomain) {
		this.eventDataDomain = eventDataDomain;
	}

	public void setJobCompletionCount(AtomicInteger jobCompletionCount) {
		this.jobCompletionCount = jobCompletionCount;
	}

	public ActorRef getSender() {
		return sender;
	}

	public void setSender(ActorRef sender) {
		this.sender = sender;
	}

	public Integer incrementJobCompletionCount() {
		return jobCompletionCount.getAndIncrement();
	}

	public Integer incrementJobInprogressCount() {
		return jobInprogressCount.getAndIncrement();
	}

	public AtomicInteger getJobCompletionCount() {
		return jobCompletionCount;
	}

	public AtomicInteger getJobInprogressCount() {
		return jobInprogressCount;
	}

	public void setJobInprogressCount(AtomicInteger jobInprogressCount) {
		this.jobInprogressCount = jobInprogressCount;
	}

	public void setJobCompletionCount(Integer jobCompletionCount) {
		this.jobCompletionCount = new AtomicInteger(jobCompletionCount);
	}

	public Map<String, String> getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Map<String, String> jobStatus) {
		this.jobStatus = jobStatus;
	}

}
