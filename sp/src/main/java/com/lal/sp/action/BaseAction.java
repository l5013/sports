package com.lal.sp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lal.sp.util.JsonTimstampProcessor;

public class BaseAction {
	
	Logger logger = Logger.getLogger(BaseAction.class);
	@Autowired
	protected HttpServletRequest request; // request
	@Autowired
	protected HttpServletResponse response; // response
	@Autowired
	protected ServletContext servletConext; // servletContext
	@Autowired
	protected HttpSession session; // session
	
public void writeToAjax(String str){
		
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html");
		
		PrintWriter write;
		try {
			write = response.getWriter();
			write.write(str);
System.out.println("==================="+str);
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * 发送成功消息
	 * @param object
	 */
	public void sendOkJSONData(Object obj){
		JSONObject json = new JSONObject();
		JsonConfig jconf = new JsonConfig();
		jconf.registerJsonValueProcessor(java.util.Date.class, new JsonTimstampProcessor());
		json.element("result", true);
		json.element("data", obj,jconf);
		writeToAjax(json.toString());
	}
	public void sendOkJSONData(Object obj,JsonConfig jconf){
		JSONObject json = new JSONObject();
		
		json.element("result", true);
		json.element("data", obj,jconf);
		writeToAjax(json.toString());
	}
	/**
	 * 发送成功消息
	 * @param record 数据，总量
	 * @param count
	 */
	public void sendOkJSONData(List record, long count){
		JSONObject json = new JSONObject();
		json.element("result", true);
		json.element("count", count);
		json.element("data", record);
		writeToAjax(json.toString());
	}
	public void sendOkJSONData(List record, long count,JsonConfig jconf){
		JSONObject json = new JSONObject();
		json.element("result", true);
		json.element("count", count);
		json.element("data", record,jconf);
		writeToAjax(json.toString());
	}
	/**
	 * 发送错误数据
	 * @param msg
	 */
	public void sendErrorJSONData(String msg){
		JSONObject json = new JSONObject();
		json.element("result", false);
		json.element("msg", "查询数据失败，请重试或联系管理员！详细信息："+msg);
		writeToAjax(json.toString());
	}
	/**
	 * 错误信息和代码
	 * @param msg
	 * @param code
	 */
	public void sendErrorJSONData(String msg,String code){
		JSONObject json = new JSONObject();
		json.element("result", false);
		json.element("msg", msg);
		json.element("errorcode", code);
		writeToAjax(json.toString());
	}
	/**
	 * 发送错误数据
	 * @param msg
	 * data
	 */
	public void sendErrorJSONData(String msg,Object data){
		JSONObject json = new JSONObject();
		json.element("result", false);
		json.element("msg", "查询数据失败，请重试或联系管理员！详细信息："+msg);
		json.element("data", data);
		writeToAjax(json.toString());
	}
	/*
	 *发送成功消息
	 * @param String... keyValue  (key value)
	 * 
	 */
	public void sendOkJSONData(String... keyValue){
		JSONObject json = new JSONObject();
		json.element("result", true);
		JSONObject json2 = new JSONObject();
		if(keyValue != null){
			String[] kv = (String[])keyValue;
			
			for(int i=0; i<kv.length-1; i++){
				if(kv[i+1].equals("true")){
					json2.element(kv[i++], true);
				}else{
					json2.element(kv[i], kv[++i]);
				}
			}
		}
		json.element("data", json2);
		writeToAjax(json.toString());
	}
	

}
