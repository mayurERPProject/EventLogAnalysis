package com.cs.dpg.assignment.application.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.dpg.assignment.application.cfg.SpringProps;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

@Service("readFileActorRun")
public class ReadFileActorRun {

	private static Logger log = Logger.getLogger(ReadFileActorRun.class.getName());

	public static Map<String, Integer> issueWithItemId = new LinkedHashMap<String, Integer>();

	public static Date errorFileCreationTime = new Date();

	@Autowired
	private ActorSystem actorSystem;

	@Autowired
	private SpringProps springProps;

	public void startActorSystemToProcess(String inputJsonString) throws Exception {
		ActorSystem actorSystem = ActorSystem.create();
		log.info("process start");
		ActorRef readFileActorRouter = this.actorSystem
				.actorOf(springProps.create(SpringProps.classNameToSpringName(ReadFileActorRouter.class)));
		Timeout timeout = new Timeout(Duration.create(150, TimeUnit.MINUTES));
		Future<Object> future = Patterns.ask(readFileActorRouter, inputJsonString, timeout);

		Object o = null;
		try {
			o = Await.result(future, timeout.duration());
		} catch (Exception e1) {
			e1.printStackTrace();
			actorSystem.stop(readFileActorRouter);
			actorSystem.shutdown();
			throw new Exception();
		}
		actorSystem.stop(readFileActorRouter);
		actorSystem.shutdown();
		if (o instanceof Exception) {
			Exception e = (Exception) o;
			actorSystem.stop(readFileActorRouter);
			actorSystem.shutdown();
			throw e;
		}
		actorSystem.stop(readFileActorRouter);
		actorSystem.shutdown();
	}
}
