package com.cs.dpg.assignment.application;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cs.dpg.assignment.application.cfg.AppConfiguration;
import com.cs.dpg.assignment.application.service.ReadFileActorRun;

public class LoggingAnalysisApplication {

	private static Logger log = Logger.getLogger(LoggingAnalysisApplication.class.getName());

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Please pass path and file name as Input param");
		}
		System.out.println("Process Start with Input file, Please check log file for further analysis:-  " + args[0]);
		log.info("File Path :- " + args[0]);
		String inputDataString = "[" + Files.readString(Paths.get(args[0]));
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ReadFileActorRun readFileActorRun = context.getBean(ReadFileActorRun.class);
		try {
			readFileActorRun.startActorSystemToProcess(inputDataString);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured");
			System.exit(0);
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		System.out.println("Process End, Please check log file for further analysis");
	}

}
