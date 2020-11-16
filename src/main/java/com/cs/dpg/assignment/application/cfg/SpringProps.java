package com.cs.dpg.assignment.application.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import akka.actor.Extension;
import akka.actor.Props;

@Component(value = "springProps")
public final class SpringProps implements Extension {
	private final ApplicationContext applicationContext;

	@Autowired
	public SpringProps(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Props create(String actorBeanName) {
		return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
	}

	public static String classNameToSpringName(Class<?> clazz) {

		String simpleName = clazz.getSimpleName();

		return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
	}
}
