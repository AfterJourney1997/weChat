package com.weChat.service;

import java.security.MessageDigest;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	private static final String token = "233";

	// 微信公众平台登录平台校验
	public boolean check(String signature, String timestamp, String nonce) {

		// 1.排序
		String[] str = new String[] { token, timestamp, nonce };
		Arrays.sort(str);
		// 2.将三个字符串拼接成一个字符串进行sha1加密
		StringBuffer content = new StringBuffer();
		for (String s : str) {
			content.append(s);
		}
		String temp = getSha1(content.toString());
		// 3.加密字符串与signature对比，表示该请求来源于微信
		return temp.equals(signature);
	}
	
	// sha1加密
	private String getSha1(String str){
		
		if(null == str || 0 == str.length()){
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
