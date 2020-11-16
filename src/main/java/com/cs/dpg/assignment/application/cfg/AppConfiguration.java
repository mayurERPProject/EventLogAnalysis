package com.cs.dpg.assignment.application.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;

@Configuration
@ComponentScan(basePackages = { "com.cs.dpg.assignment.application.*" })
public class AppConfiguration {

	@Bean
	public ActorSystem actorSystem() {

		ActorSystem system = ActorSystem.create("akka-crimes-processing-system", akkaConfiguration());

		return system;
	}

	@Bean
	public Config akkaConfiguration() {
		return ConfigFactory.load();
	}
}
