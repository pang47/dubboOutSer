package com.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.common.SendDubboMsgUtils;

@RestController
@RequestMapping("data")
public class CommonDataAction {
	@RequestMapping("commonInvoke")
	public JSONObject getJsonData(String serviceId, String methodId, JSONObject inputJson){
		JSONObject rtnJson = new JSONObject();
		if(!StringUtils.isEmpty(serviceId) && !StringUtils.isEmpty(serviceId)){
			//rtnJson = SendDubboMsgUtils.sendJSON(serviceId, methodId, inputJson);
		}else{
			rtnJson.put("rtnCode", "-1");
			rtnJson.put("msg","失败");
		}
		return rtnJson;
	}
}
