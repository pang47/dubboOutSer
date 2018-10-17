package com.util;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private String retCode; // 返回码0代表交易成功，没有异常
	private T retObj;
	private String retMsg;

	public Result() {
	}

	public Result(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public Result(String retCode, T retObj) {
		this.retCode = retCode;
		this.retObj = retObj;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public T getRetObj() {
		return retObj;
	}

	public void setRetObj(T retObj) {
		this.retObj = retObj;
	}

}
