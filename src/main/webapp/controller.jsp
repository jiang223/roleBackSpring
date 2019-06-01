<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.baidu.ueditor.ActionEnter" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	String mes=new ActionEnter( request, rootPath ).exec();
	Gson gson = new GsonBuilder().create();
	try {
		Map<String,String>map = gson.fromJson(mes, new  TypeToken<Map<String,String>>(){}.getType());
		if(map.get("url")!=null){
			map.put("url","http://www.bmafma.com:8080"+map.get("url"));
		}
	mes = gson.toJson(map);
	}catch (Exception E){

	}
	out.write( mes );
	
%>