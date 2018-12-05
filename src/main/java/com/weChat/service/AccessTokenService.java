package com.weChat.service;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weChat.bean.AccessToken;


@Service
public class AccessTokenService {
	
	// 应该写到配置文件中读取出来
	private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String APPID = "wxf4b35045d7b53425";
	private static final String APPSECRET = "0cb19979042027eeafc5e935edbbc247";
	
	// 储存accesstoken
	private static AccessToken at;
	
	// 外部可调用的获取accesstoken方法
	public static String getAccessToken(){
		if(at == null || at.isExpired()){
			getAccessTokenForInner();
		}
		return at.getAccessToken();
	}
	
	// 获取token
	private static void getAccessTokenForInner(){
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr = getResquest(url);
		JsonObject jsObj = (JsonObject) new JsonParser().parse(tokenStr);
		String accessToken = jsObj.get("access_token").getAsString();
		String expireIn = jsObj.get("expires_in").getAsString();
		//创建accesstoken对象并储存；
		at = new AccessToken(accessToken, expireIn);
		
	}
	
	//向指定的地址发送get请求
	public static String getResquest(String url){
		
		try {
			URL urlObj = new URL(url);
			//开连接
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while((len=is.read(b))!=-1){
				sb.append(new String(b,0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
