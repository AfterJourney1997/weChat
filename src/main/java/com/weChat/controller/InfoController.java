package com.weChat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weChat.service.DownloadService;
import com.weChat.service.InfoService;

@Controller
@RequestMapping("/wx")
public class InfoController {

	@Autowired
	private InfoService infoService;
	
	@Autowired
	private DownloadService downloadService;
	
	//接收用户消息
	@RequestMapping(method={RequestMethod.POST})
	@ResponseBody
	public void getInformation(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//处理消息的xml
			Map<String,String> requestMap = infoService.parseRequset(request.getInputStream());
			System.out.println(requestMap);
			if(requestMap.get("MsgType").equals("image")){
				String savePath="D:\\wx\\";
				String saveFileName= requestMap.get("MediaId") + ".jpg";
				//System.out.println(savePath);
				//System.out.println(saveFileName);
				downloadService.download(requestMap.get("MediaId"), savePath, saveFileName);
			}else if(requestMap.get("MsgType").equals("voice")){
				String savePath="D:\\wx\\";
				String saveFileName= requestMap.get("MediaId") + ".amr";
				//System.out.println(savePath);
				//System.out.println(saveFileName);
				downloadService.download(requestMap.get("MediaId"), savePath, saveFileName);
			}else if(requestMap.get("MsgType").equals("text")){
				//System.out.println("测试！");
				Logger log = Logger.getLogger(InfoController.class);
				log.info(requestMap.get("Content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
