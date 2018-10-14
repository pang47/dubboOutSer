package com.action.ehc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.util.Constants;
import com.util.MD5Util;
import com.util.Result;
import com.util.SecurityUtil;

@RestController
@RequestMapping(value="ehc")
public class EhcController {
	
	private static final Logger logger = LoggerFactory.getLogger(EhcController.class);
	
	@RequestMapping(value="MD5Util",method=RequestMethod.POST)
	@ResponseBody
	public Result md5Util(@RequestBody JSONObject reqObj) {
		Result result = new Result();
		String timestamp = String.valueOf(System.currentTimeMillis());
		JSONObject request = reqObj.getJSONObject("reqObj");
		// 签名数据集合
		reqObj.put("timestamp", timestamp);
		Map<String, String> signMap = new TreeMap<String, String>();
		Set<Entry<String, Object>> entrys = request.entrySet();
		// 获取签名键值
		for (Entry<String, Object> entry : entrys) {
			// 非空 且 非过滤签名组合
			if (StringUtils.isNotEmpty(request.getString(entry.getKey())) && !"sign".contains(entry.getKey())) {
				signMap.put(entry.getKey(), request.getString(entry.getKey()));
			}
		}
		String retStr = getSignContent(signMap);
		retStr += "key=" + reqObj.getString("appkey");
		String signType = signMap.get("sign_type");
		String encryptDate = "";
		if (StringUtils.isEmpty(signType) || "MD5".toString().equals(signType)) {
			logger.info("Sign Before MD5:" + retStr);
			encryptDate = MD5Util.encrypt(retStr).toUpperCase();
			logger.info("Sign Result MD5:" + encryptDate);
		}
		JSONObject retObj = new JSONObject();
		retObj.put("sign", encryptDate);
		retObj.put("beforeSign", retStr);
		result.setRetCode(Constants.successCode);
		result.setRetObject(retObj);
		return result;
	}
	
	@RequestMapping(value="sendPost",method=RequestMethod.POST)
	@ResponseBody
	public Result sendPost(@RequestBody JSONObject reqObj){
		Result retObj = new Result();
		
		//获取reqObj内的参数
		String urlStr = reqObj.getString("url");
		String appkey = reqObj.getString("appkey");
		JSONObject sendObj = reqObj.getJSONObject("reqObj");
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json; charset=GBK");
		String responseXml = "";
		String requestMessage = JSON.toJSONString(sendObj);
		StringBuffer retMsg = new StringBuffer();
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = null;
			OutputStream writer = null;
			InputStream inputStream = null;
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(90000);
			conn.setUseCaches(false);
			for (Entry<String, String> entry : headers.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			writer = conn.getOutputStream();
			writer.write(requestMessage.getBytes("UTF-8"));
			logAndMsg("------------------向外发送" + "请求------------------", retMsg);
			logAndMsg("请求URL:" + url, retMsg);
			logAndMsg("请求报文体:" + requestMessage, retMsg);
			logAndMsg("-------------------------------------------------------------", retMsg);
			String sCurrentLine = "";
			if (conn.getResponseCode() == 200) {
				inputStream = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

				while ((sCurrentLine = reader.readLine()) != null) {
					responseXml = responseXml + sCurrentLine;
				}
			} else {
				logger.info("响应错误：" + conn.getResponseCode() + ", " + conn.getResponseMessage());
				retObj.setRetCode(Constants.netErrorCode);
				retObj.setRetMsg("响应错误:" + conn.getResponseCode() + ", " + conn.getResponseMessage());
				return retObj;
			}
			logAndMsg("响应报文 解密前:" + responseXml, retMsg);
			if (StringUtils.isNotEmpty(responseXml)) {
				JSONObject res = JSONObject.parseObject(responseXml);
				if (StringUtils.isNotEmpty(res.getString("biz_content"))) {
					res.put("biz_content",SecurityUtil.decrypt(res.getString("biz_content"), res.getString("enc_type"), appkey, "appId"));
				}
				logAndMsg("响应报文 解密后:" + JSONObject.toJSONString(res), retMsg);
				String signOld = res.getString("sign");
				String signNew = createSign(res, reqObj.getString("appkey"), retMsg);
				if (StringUtils.isEmpty(signOld) || !signOld.equals(signNew)){
					retObj.setRetCode(Constants.unknownErrorCode);
					retObj.setRetMsg("返回报文验签失败");
					retObj.setRetObject(null);
					return retObj;
				}
			} else {
				retObj.setRetCode(Constants.isEmptyCode);
				retObj.setRetMsg(Constants.EmptyMsg);
				retObj.setRetObject(null);
				return retObj;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			retObj.setRetCode(Constants.unknownErrorCode);
			retObj.setRetMsg(e.getMessage());
			return retObj;
		} catch (IOException e) {
			e.printStackTrace();
			retObj.setRetCode(Constants.unknownErrorCode);
			retObj.setRetMsg(e.getMessage());
			return retObj;
		} catch (Exception e) {
			e.printStackTrace();
			retObj.setRetCode(Constants.unknownErrorCode);
			retObj.setRetMsg(e.getMessage());
			return retObj;
		}
		retObj.setRetData(retMsg.toString());
		retObj.setRetCode(Constants.successCode);
		return retObj;
	}
	
	private static String getSignContent(Map<String, String> map) {

		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isNotEmpty(entry.getValue())) {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.substring(0, sb.length() - 1);
		return result;
	}
	
	private String createSign(JSONObject request, String appkey, StringBuffer sb){
		Map<String, String> signMap = new TreeMap<String, String>();
		Set<Entry<String, Object>> entrys = request.entrySet();
		for (Entry<String, Object> entry : entrys) {
			// 非空 且 非过滤签名组合
			if (StringUtils.isNotEmpty(request.getString(entry.getKey())) && !"sign".contains(entry.getKey())) {
				signMap.put(entry.getKey(), request.getString(entry.getKey()));
			}
		}
		String retStr = getSignContent(signMap);
		retStr += "key=" + appkey;
		String signType = signMap.get("signType");
		String encryptDate = "";
		if (StringUtils.isEmpty(signType) || "MD5".toString().equals(signType)) {
			logAndMsg("Sign Before MD5:" + retStr, sb);
			encryptDate = MD5Util.encrypt(retStr).toUpperCase();
			logAndMsg("Sign Result MD5:" + encryptDate, sb);
		}
		return encryptDate;
	}
	
	private void logAndMsg(String msg, StringBuffer sb){
		sb.append(msg);
		sb.append(";");
		logger.info(msg);
	}
}
