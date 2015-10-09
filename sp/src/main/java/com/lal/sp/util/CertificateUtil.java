package com.lal.sp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 身份证信息工具类
 * @author 李安亮
 *
 */
public class CertificateUtil {
	
	/**
	 * 根据身份证号码返回出生日期
	 * @param cNo 身份证号码
	 * @return
	 * @throws ParseException 
	 */
	public static String getBirthDay(String cNo) {
		String birthday = "";
		if(cNo.length()==18) {
			birthday = cNo.substring(6, 14);
		}else if(cNo.length()==15) {
			birthday = "19" + cNo.substring(6,12);
		}else {
			throw new RuntimeException("身份证格式错误！");
		}
		String year = birthday.substring(0,4);
		String month = birthday.substring(4,6);
		String day = birthday.substring(6,8);
		
		return year+"-"+month+"-"+day;
	}
	
	public static String getBirthDayByCNo(String cNo) throws ParseException {
		String birthday = "";
		Date birthdate = null;
		if(cNo.length()==18) {
			birthday = cNo.substring(6, 14);
			birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
		}else if(cNo.length()==15) {
			birthday = cNo.substring(6,12);
			birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
		}else {
			throw new RuntimeException("身份证格式错误！");
		}
		birthday = new SimpleDateFormat("yyyy-MM-dd").format(birthdate);
		return birthday;
	}

}
