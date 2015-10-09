package com.lal.sp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonTimstampProcessor implements JsonValueProcessor{

	private String format="yyyy-MM-dd HH:mm:ss";   
	public Object processArrayValue(Object arg0, JsonConfig conf) {
		return null;
	}

	public JsonTimstampProcessor() {
	}
	
	public JsonTimstampProcessor(String format) {
		this.format=format;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig config) {
		if(value==null)   
              return "";   
             if (value instanceof java.sql.Timestamp) {   
              String str = new SimpleDateFormat(format).format(new Date(((java.sql.Timestamp)value).getTime()));   
              return str;   
             }else if(value instanceof java.util.Date){
            	 String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date(((java.util.Date)value).getTime()));   
                 return str; 
             }
             return value.toString();   
	}
}
