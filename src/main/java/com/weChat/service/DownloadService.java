package com.weChat.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	public void download(String mediaId, String savePath, String saveFileName){
		
		try {
			//拼装请求地址
			String downloadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
			downloadMediaUrl = downloadMediaUrl.replace("ACCESS_TOKEN", AccessTokenService.getAccessToken());
			downloadMediaUrl = downloadMediaUrl.replace("MEDIA_ID", mediaId);
			//System.out.println(AccessTokenService.getAccessToken());
			URL url = new URL(downloadMediaUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			InputStream is = con.getInputStream();
			InputStream inStream = is;
			byte[] data = readInputStream(inStream);
			File mediaFile = new File(savePath+saveFileName);
			FileOutputStream outStream = new FileOutputStream(mediaFile);
			outStream.write(data);
			outStream.close();
			//System.out.println(is.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
		outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
		
}
