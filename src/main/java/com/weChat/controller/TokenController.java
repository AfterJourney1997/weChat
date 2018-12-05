package com.weChat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weChat.service.TokenService;

@Controller
@RequestMapping("/wx")
public class TokenController {
	
	@Autowired
	TokenService tokenService;
	
	// 接收微信平台token验证
	@RequestMapping(method={RequestMethod.GET})
	@ResponseBody
	public void token(HttpServletRequest request,HttpServletResponse response){
		//获取微信后台传入的四个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(tokenService.check(signature, timestamp, nonce)){
        	try {
				PrintWriter pw = response.getWriter();
				pw.println(echostr);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
}
