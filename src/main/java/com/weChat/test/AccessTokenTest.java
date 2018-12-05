package com.weChat.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weChat.service.AccessTokenService;

public class AccessTokenTest {
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	@Test
	public void getToken(){
		System.out.println(AccessTokenService.getAccessToken());
		System.out.println(AccessTokenService.getAccessToken());
	}
}
