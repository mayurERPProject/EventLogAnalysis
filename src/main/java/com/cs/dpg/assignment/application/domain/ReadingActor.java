package com.cs.dpg.assignment.application.domain;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cs.dpg.assignment.application.repository.CreateEventDataRepository;

import akka.actor.UntypedActor;

@Component("readingActor")
@Scope("prototype")
public class ReadingActor extends UntypedActor {

	private static Logger log = Logger.getLogger(ReadingActor.class.getName());
	@Autowired
	private CreateEventDataRepository createEventData;

	@Override
	public void onReceive(Object message) {
		if (message instanceof ReadFileRequest) {
			ReadFileRequest readFileRequest = (ReadFileRequest) message;
			try {
				ReadFileResponse response = createEventData(readFileRequest);
				getSender().tell(response, getSelf());
			} catch (Exception e) {
				getSender().tell(e, getSelf());
				getContext().stop(self());
				return;
			}
		} else {
			unhandled(message);
		}
	}

	private ReadFileResponse createEventData(ReadFileRequest readFileRequest) throws Exception {
		log.info("ReadingActor to insert the event data into DB");
		ReadFileResponse readFileResponse = new ReadFileResponse();
		readFileResponse.setJobId(readFileRequest.getJobId());
		boolean flag = createEventData.createEventData(readFileRequest);
		readFileResponse.setFlag(flag);
		log.info("ReadingActor completed the event against DB with flag :- " + flag);
		return readFileResponse;
	}
}
