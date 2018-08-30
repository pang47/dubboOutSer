package com.util;

import com.alibaba.fastjson.JSONObject;

public class Result {
	private String retCode; // 返回码0代表交易成功，没有异常
	private JSONObject retObject;
	private String retMsg;
	
	public Result(){}
	
	public Result(String retCode,String retMsg){
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	
	public Result(String retCode,JSONObject retJson){
		this.retCode = retCode;
		this.retObject = retJson;
	}
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public JSONObject getRetObject() {
		return retObject;
	}
	public void setRetObject(JSONObject retObject) {
		this.retObject = retObject;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}	
}
