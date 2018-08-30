package com.common;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.service.CommonService;
import com.util.InputObject;


public class SendDubboMsgUtils implements ServletContextListener{
	
	private static CommonService commonService;
	private static final Logger log = LoggerFactory.getLogger(CommonService.class);
	
	public static JSONObject sendJSON(InputObject input) throws Exception{
		//CommonService commonService = (CommonService) BeanFactoryUtils.getInstance().getBeanFactory().getBean("ct_dubbo_consumer");
		//CommonService commonService = (CommonService) factory.getBean("ct_dubbo_consumer");
		if(StringUtils.isEmpty(input.getService_id())||StringUtils.isEmpty(input.getMethod_id()))
			throw new Exception("参数不全");
		log.info("发送报文:"+input.getService_id()+"."+input.getMethod_id()+";"+input.getParams());
		JSONObject inputJson = new JSONObject();
		inputJson.put("serviceName", input.getService_id());
		inputJson.put("methodName", input.getMethod_id());
		inputJson.put("params", input.getParams());
		log.info("转化后报文:" + inputJson);
		JSONObject retJson = commonService.execute(inputJson);
		if(retJson.containsKey("list"))
			retJson.put("object", retJson.get("list"));
		log.info("返回报文:"+retJson.toJSONString());
		return retJson;
	}

	/**	
	 * web停止前调用
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	/**
	 * web启动后调用
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		this.commonService = (CommonService) context.getBean("ct_dubbo_consumer");
	}

}
