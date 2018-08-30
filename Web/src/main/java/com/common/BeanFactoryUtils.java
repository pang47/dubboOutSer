package com.common;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryUtils {
	private static BeanFactoryUtils instance;
	
	private BeanFactory beanFactory;
	
	public BeanFactoryUtils(){
		setBeanFactory(new ClassPathXmlApplicationContext("spring/spring-dubbo-consumer.xml"));
	}
	
	public static BeanFactoryUtils getInstance(){
		if(instance == null){
			synchronized (BeanFactoryUtils.class) {
				if(instance == null){
					instance = new BeanFactoryUtils();
				}
			}
		}
		return instance;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	
}
