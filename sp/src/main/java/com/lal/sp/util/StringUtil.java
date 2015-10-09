package com.lal.sp.util;


import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StringUtil {
	public static final String WORDS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_"; // 生成随机字符串的字符集

	/*
	 * 判断一个字符串是否为空。
	 */
	public static boolean isEmptyString(String param) {
		return (param == null || param.trim().length() == 0);
	}

	/*
	 * 判断一个字符串是否为空，如果为空就转化为nul值
	 */
	public static String convertToNull(String strValue) {
		if (isEmptyString(strValue))
			return null;
		else if ("null".equalsIgnoreCase(strValue))
			return null;
		else
			return strValue.trim();
	}

	/*
	 * 生成随即字符串
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(WORDS.charAt(random.nextInt(WORDS.length())));
		}
		return sb.toString();
	}

	/*
	 * 替换字符串
	 */
	public static String replace(String src, String matcher, String replacement) {
		if (src == null) {
			return "";
		}
		int from = 0, end = 0;
		StringBuffer sb = new StringBuffer();
		while ((end = src.indexOf(matcher, end)) != -1) {
			sb.append(src.substring(from, end)).append(replacement);
			from = end + matcher.length();
			end += replacement.length();
		}
		sb.append(src.subSequence(from, src.length()));
		return sb.toString();
	}

	/*
	 * 修正查询条件语句
	 */
	public static String fixQueryString(String queryValue) {
		if (queryValue == null || "".equals(queryValue)) {
			return queryValue;
		}
		queryValue = queryValue.replaceAll("\\'", "\\'\\'");
		queryValue = queryValue.replaceAll("%", "@%");
		if (queryValue.indexOf("@") > 0) {
			queryValue = queryValue + "ESCAPE '@'";
		}
		return queryValue;
	}

	/*
	 * 创建排序字符传,按照时间先后顺序。
	 */
	public static String createOrderNumeric(String orderType) {
		StringBuffer str = new StringBuffer();
		str.append("0").append(orderType);
		Calendar c = Calendar.getInstance();
		str.append(c.get(Calendar.YEAR)).append(c.get(Calendar.MARCH));
		str.append(c.get(Calendar.DAY_OF_MONTH)).append(
				c.get(Calendar.HOUR_OF_DAY));
		str.append(c.get(Calendar.MINUTE)).append(c.get(Calendar.SECOND));
		return c.toString();
	}

	/*
	 * 将字符数组转化为包含分割符的字符串。
	 */
	public static String convertArrayToString(String[] idString) {
		StringBuffer buffer = new StringBuffer();
		if (idString != null) {
			for (int i = 0; i < idString.length; i++) {
				buffer.append("'").append(idString[i]).append("'").append(",");
			}
			buffer.deleteCharAt(buffer.lastIndexOf(","));
		}

		return buffer.toString();
	}

	/**
	 * 此方法将返回一个定长的，在源串前补“a”的字符串
	 * 
	 * 
	 */
	public static String preFormatString(String sourceString, int nlength,
			String a) {
		String sZero = "";
		if (sourceString != null) {
			for (int i = 0; i < nlength - sourceString.length(); i++) {
				sZero += a;
			}
			sZero += sourceString;
		}
		return sZero;
	}

	/**
	 * @描述：返回指定个数的特定字符串
	 * @日期：2007-12-16
	 * @param sourceString
	 * @param length
	 * @param specifyStr
	 * @return
	 */
	public static String getAmountString(int length, String specifyStr) {
		StringBuffer specify = new StringBuffer();
		for (int i = 0; i < length; i++)
			specify.append(specifyStr);
		return specify.toString();
	}
	/**
	 * 生成唯一id，32位
	 * @return String
	 * @author ChenZhedong
	 * @data 2010-04-26
	 */
	public static String getUniqueId() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.replace("-", "");
	}
	/**
	 * 看一个字符串是不是全部由数字组成
	 * 数字是0-9的
	 * @param str
	 * @return boolean
	 * @author ChenZhedong
	 * @data 2010-04-27
	 */
	public static boolean emptyNumString(String str){
		boolean result = false;
		int count = 0;
		//如果字符串为空值或者空串，将返回结果设置为false
		if(null == str || str.length() == 0){
			result = false;
		}
		//如果不是空值或者空串，看字符串是否都是数字
		else{
			//转换成字符数组
			char[] strArray = str.toCharArray();
			//循环看每个是不是都是1-9
			for(int i=0;i<strArray.length;i++){
				char temp = strArray[i];
				if(47 < temp && temp < 58){
					count++;
				}
			}
			//如果都是1-9，将返回结果设置为true
			if(count == str.length()){
				result = true;
			}
		}
		return result;
	}
	public static String getNowDate(){
		String date=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		return date;
	}
	/**
	 * 验证 字符串是否为标准 E-mail格式
	 * @author ZhangDefu
	 * @param mail
	 * @return 是 true; 否 false
	 * @date 2010年7月14日13:45:29
	 */
	public static boolean checkEmail(String mail){  
	    String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  
	    Pattern   p   =   Pattern.compile(regex);  
	    Matcher   m   =   p.matcher(mail);  
	    return m.find();  
	}  
	
	
	
	/**
	 * 四舍五入
	 * @param index
	 * @param number
	 * @return
	 */
	public static double getRound(int index, double number) {
        if (number == 0)
            return 0;
        if (index < 0)
            throw new IllegalArgumentException("error param");
        
        String temp = String.valueOf(number);
        BigDecimal b = new BigDecimal(temp);
        BigDecimal one = new BigDecimal(1);

        return b.divide(one, index, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	/**
	 * 根据 当前时间 构造一个唯一的标识
	 * @return
	 */
	public static String generateDateName() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(100);
		return formatDate + random;
	}
	
	public static String parseUTF8(String old){
		String str="";
		try {
			str=new String(old.getBytes("iso-8859-1"),"UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
