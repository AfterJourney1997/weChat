package com.weChat.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

	public Map<String, String> parseRequset(InputStream is) {
		
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		try {
			// 读取输入流，获取文档对象
			Document document = reader.read(is);
			//根据文档对象获取根节点
			Element root = document.getRootElement();
			//获取根节点所有的子节点
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	

}
