package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.common.SendDubboMsgUtils;
import com.util.InputObject;
import com.util.Result;

@RestController
@RequestMapping("test")
public class TestAction {
	
	private static Logger log = LoggerFactory.getLogger(TestAction.class);
	
	@RequestMapping("example")
	public String example(){
		return "example";
	}
	
	@RequestMapping("dubbo")
	@ResponseBody
	public Result dubbo(InputObject input){
		JSONObject inputJson = new JSONObject();
		JSONObject retJson = new JSONObject();
		try{
			retJson = SendDubboMsgUtils.sendJSON(input);
		}catch(Exception e){
			log.error("抛出异常:",e);
			return new Result("-1",e.getMessage());
		}
		return new Result("0", retJson);
	}
	
}
