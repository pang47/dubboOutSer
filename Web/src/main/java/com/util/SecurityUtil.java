package com.util;

/**
 * 安全工具类： 主要功能有加解密、签名、验签等
 * 
 * @author sun
 * @version 1.0.0
 */

public class SecurityUtil {

	/**
	 * 通过AES加密方式完成文本加密
	 * 
	 * @author sun
	 * @date 2014年6月20日
	 * @param data
	 *            待加密文本
	 * @return 加密后文本
	 * @throws Exception
	 */
	public static String encryptByAES(String data, String appSecret, String appId) throws Exception {
		return AESUtil.encrypt(data, appSecret);
	}

	/**
	 * 通过DES加密方式完成文本加密
	 * 
	 * @param data
	 *            待加密文本
	 * @return 加密后文本
	 * @throws Exception
	 */
	public static String encryptByDES(String data, String appSecret, String appId) throws Exception {
		return DESUtil.encrypt(data, appSecret.substring(0, 8));
	}

	/**
	 * 通过DES解密方式完成密文解密
	 * 
	 * @param data
	 *            待解密文本
	 * @return
	 * @throws Exception
	 */
	public static String decryptByDES(String data, String appSecret, String appId) throws Exception {
		return DESUtil.decrypt(data, appSecret.substring(0, 8));
	}

	/**
	 * 通过AES解密方式完成密文解密
	 * 
	 * @param data
	 *            待解密文本
	 * @param platformCode
	 *            平台号
	 * @return
	 * @throws Exception
	 */
	public static String decryptByAES(String data, String appSecret, String appId) throws Exception {
		return AESUtil.decrypt(data, appSecret);
	}

	/*
	 * 字符串加密
	 * 
	 * @author sun
	 * 
	 * @date 2016年10月13日
	 * 
	 * @param text 待加密的字符串
	 * 
	 * @param encryptType 加密类型
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String encrypt(String text, String encryptType, String appSecret, String appId) throws Exception {
		if (encryptType == null || "".equals(encryptType) || EncType.Plain.toString().equals(encryptType))
			return text;
		else if (EncType.AES.toString().equals(encryptType))
			return SecurityUtil.encryptByAES(text, appSecret, appId);
		else
			return text;
	}

	/*
	 * 字符串加密
	 * 
	 * @author sun
	 * 
	 * @date 2016年10月13日
	 * 
	 * @param text 待加密的字符串
	 * 
	 * @param encryptType 加密类型
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String decrypt(String text, String encryptType, String appSecret, String appId) throws Exception {
		if (encryptType == null || "".equals(encryptType) || EncType.Plain.toString().equals(encryptType))
			return text;
		else if (EncType.AES.toString().equals(encryptType))
			return SecurityUtil.decryptByAES(text, appSecret, appId);
		else if (EncType.DES.toString().equals(encryptType))
			return SecurityUtil.decryptByDES(text, appSecret, appId);
		else
			return text;
	}

}
