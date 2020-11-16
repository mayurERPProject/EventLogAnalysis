package com.cs.dpg.assignment.application.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cs.dpg.assignment.application.cfg.SpringProps;
import com.cs.dpg.assignment.application.domain.EventDataDomain;
import com.cs.dpg.assignment.application.domain.ReadFileRequest;
import com.cs.dpg.assignment.application.domain.ReadFileResponse;
import com.cs.dpg.assignment.application.domain.ReadingActor;
import com.cs.dpg.assignment.application.domain.RouterData;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

@Component("readFileActorRouter")
@Scope("prototype")
public class ReadFileActorRouter extends UntypedActor {

	private RouterData routerStatus = new RouterData();
	private static Logger log = Logger.getLogger(ReadFileActorRouter.class.getName());

	@Autowired
	private ReadAndParsingDataService readAndParsingDataService;

	@Autowired
	private SpringProps springProps;

	private ActorRef readFilePool;

	@Override
	public void onReceive(Object obj) {
		try {
			if (obj instanceof String) {
				String inputString = (String) obj;
				Map<String, EventDataDomain> eventDataMap = readAndParsingDataService
						.readAndParseEventData(inputString);
				log.info("Final Object is going to insert into DB :- " + eventDataMap);
				readFilePool = getContext()
						.actorOf(springProps.create(SpringProps.classNameToSpringName(ReadingActor.class))
								.withRouter(new RoundRobinRouter(10)));
				routerStatus.setSender(getSender());
				for (String key : eventDataMap.keySet()) {
					ReadFileRequest request = new ReadFileRequest();
					request.setJobId(getUniqueID());
					request.setEventDataDomain(eventDataMap.get(key));
					routerStatus.incrementJobInprogressCount();
					routerStatus.getJobStatus().put(request.getJobId(), "Inprogress");
					readFilePool.tell(request, getSelf());
				}
			} else if (obj instanceof ReadFileResponse) {
				ReadFileResponse response = (ReadFileResponse) obj;
				routerStatus.getJobStatus().put(response.getJobId(), "Completed");
				routerStatus.incrementJobCompletionCount();
				if (routerStatus.getJobCompletionCount().get() % 10 == 0) {
					log.info("GC Executed");
					Runtime.getRuntime().gc();
				}
				log.info("Response Result With CompletionCount:- " + routerStatus.getJobCompletionCount());
				log.info("Response Result With Total Count:- " + routerStatus.getJobInprogressCount());
				if (routerStatus.getJobCompletionCount().get() == routerStatus.getJobInprogressCount().get()) {
					log.info("Response JOB STOPS BEFORE TELL:- ");
					routerStatus.getSender().tell(routerStatus.getEventDataDomain(), getSelf());
					getContext().stop(self());
					log.info("Response JOB STOPS :- ");
				}
				log.info("Response Result :- ");
			} else if (obj instanceof Exception) {
				Exception e = (Exception) obj;
				throw e;
			}
		} catch (IOException e) {
			log.error("Error while processing the data in DB using Akka thread", e);
			routerStatus.getSender().tell(e, getSelf());
			getContext().stop(self());
			return;
		} catch (Exception e) {
			log.error("Error while processing the data in DB using Akka thread", e);
			routerStatus.getSender().tell(e, getSelf());
			getContext().stop(self());
			return;
		}
	}

	private String getUniqueID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
	}

}
