package com.util;


import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ParentController {
	private  final Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 6357869213649815390L;
	public Map<String,Object> resultError(String msg){
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> meta=new HashMap<String,Object>();
		map.put("success",false);
		map.put("message",msg);
		//map.put("meta",meta);
		Map<String,Object>data=new HashMap<>();
		map.put("data", null);
		return map;
	}
	public Map<String,Object> resultError(String msg,int code){
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> meta=new HashMap<String,Object>();
		map.put("success",false);
		map.put("message",msg);
		//map.put("meta",meta);
		Map<String,Object>data=new HashMap<>();
		map.put("data", null);
		return map;
	}
	public Map<String,Object> resultSucess(Object obj){
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> meta=new HashMap<String,Object>();
		map.put("success",true);
		map.put("message","操作成功");
		//map.put("meta",meta);
		map.put("data", obj);
		return map;

	}
	public Map<String,Object> resultSucess(Object obj,Object pdj){
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> meta=new HashMap<String,Object>();
		map.put("success",true);
		map.put("message","操作成功");
		//map.put("meta",meta);
		map.put("pagination",pdj);
		map.put("data", obj);
		return map;
	}

}
