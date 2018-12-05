package com.weChat.bean;

public class AccessToken {
	
	//获取到的access_token
	private String accessToken; 
	
	//access_token过期时间
	private long expireTime;

	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		this.expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpirTime() {
		return expireTime;
	}

	public void setExpirTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	//判断accesstoken是否过期
	public boolean isExpired(){
		return (System.currentTimeMillis()>expireTime);
	}
	
}
