package com.hzdp.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hzdp.web.api.IAction;

@Component
public class ActionFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ActionFactory.applicationContext = applicationContext;
	}

	public static IAction createAction(String actionKey) {
		if (actionKey == null || applicationContext == null) {
			return null;
		}

		return (IAction) applicationContext.getBean(actionKey);
	}

}
