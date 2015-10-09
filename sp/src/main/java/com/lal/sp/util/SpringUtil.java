package com.lal.sp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private static SpringUtil stools = null;

	public synchronized static SpringUtil init() {
		if (stools == null) {
			stools = new SpringUtil();
		}
		return stools;
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.out.println("acontext");
		
		SpringUtil.applicationContext = arg0;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
