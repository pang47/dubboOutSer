package com.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.alibaba.fastjson.JSONObject;

public class SendJsonService implements BeanFactoryAware{

	private BeanFactory factory;
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;
	}

	public JSONObject sendJSON(String serviceName, String methodName, JSONObject inputJson){
		//CommonService commonService = (CommonService) BeanFactoryUtils.getInstance().getBeanFactory().getBean("ct_dubbo_consumer");
		CommonService commonService = (CommonService) factory.getBean("ct_dubbo_consumer");
		inputJson.put("serviceName", serviceName);
		inputJson.put("methodName", methodName);
		JSONObject retJson = commonService.execute(inputJson);
		System.out.println("retJson:"+retJson.toJSONString());
		return retJson;
	}
}
