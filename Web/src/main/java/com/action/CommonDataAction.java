package com.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.common.SendDubboMsgUtils;
import com.util.Constants;
import com.util.InputObject;
import com.util.Result;

@RestController
@RequestMapping("data")
public class CommonDataAction {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDataAction.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="commonInvoke",method=RequestMethod.POST)
	@ResponseBody
	public Result getJsonData(InputObject input) {
		JSONObject retJson = new JSONObject();
		try{
			retJson = SendDubboMsgUtils.sendJSON(input);
		}catch(Exception e){
			logger.error("抛出异常:",e);
			return new Result(Constants.UNKNOWERRORCODE,e.getMessage());
		}
		return new Result(Constants.SUCCESSCODE,retJson.get("list"));
	}
}
