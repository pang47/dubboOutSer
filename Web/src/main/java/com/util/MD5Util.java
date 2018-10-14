package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类 功能描述： 2014年1月10日
 * 
 * @author zwsun
 * 
 */
public class MD5Util {

	/**
	 * 适用于上G大的文件
	 * 
	 * @param file
	 * @return 加密的密文
	 * @throws IOException
	 */
	public static String encrypt(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		MessageDigest messagedigest = null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.out.println("初始化失败，MessageDigest不支持MD5Util，原因是：" + nsaex.getMessage());
		}
		in.close();
		messagedigest.update(byteBuffer);
		return DataFormater.byte2hex(messagedigest.digest());
	}

	/**
	 * MD5加密
	 * 
	 * @param s
	 *            明文
	 * @return 密文
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String s) {
		try {
			return encrypt(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("密码验证出异常了");
			return "";
		}
	}
	
	public static void main(String[] args) {
		String str = "app_id=1CDMENSA2000870BA8C0000093B8423B&biz_content={\"ehealth_code\":\"2BB96ED784AED221D7B00AA00F27158CFD1DCCAD14881C5AF7CA821042EF0B19:0:7A25BD776D9252C2D30F020E83711D75::35020001001\",\"out_verify_no\":\"1000000030\",\"out_verify_time\":\"20180525153521000\",\"operator_id\":\"12917\",\"operator_name\":\"系统管理\",\"treatment_code\":\"010101\"}&enc_type=Plain&method=all&sign_type=MD5&term_id=3502000401002&timestamp=20180525153521000&version=X.M.0.1key=FPlJcNpMyywvcshHeBGXwZZKlAsgrIna";
		try {
			System.out.println(encrypt(str.getBytes("GBK")).toUpperCase());;
		} catch (UnsupportedEncodingException e) {
			System.out.println("密码验证出异常了");
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param bytes
	 *            明文
	 * @return 密文
	 */
	public static String encrypt(byte[] bytes) {
		MessageDigest messagedigest = null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.out.println("初始化失败，MessageDigest不支持MD5Util，原因是：" + nsaex.getMessage());
		}
		messagedigest.update(bytes);
		return DataFormater.byte2hex(messagedigest.digest());
	}

	/**
	 * 密码校验
	 * 
	 * @param password
	 *            明文密码
	 * @param md5PwdStr
	 *            密文
	 * @return 相等：true，不相等：false
	 */
	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = "";
		s = encrypt(password);
		return s.equals(md5PwdStr);
	}
}
