package com.cs.dpg.assignment.application.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cs.dpg.assignment.application.domain.ReadFileRequest;

@Repository
public class CreateEventDataRepository {

	private static Logger log = Logger.getLogger(CreateEventDataRepository.class.getName());

	public boolean createEventData(ReadFileRequest readFileRequest) {
		log.info("DB is not installed on machine, find required param as Input request :- "
				+ readFileRequest.getEventDataDomain().getEventId());
		return false;
	}

}
