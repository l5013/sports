package com.lal.sp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tools {

	/**
	 * 判断当前字符是否为空
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(String val){
		if(val==null)return true;
		if(val.trim().length()==0)return true;
		return false;
	}
	/**
	 * 判断当前字符是否不为�?
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(String val) {
		return !isEmpty(val);
	}
	
	/**
	 * 验证手机�?
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if(Tools.isEmpty(mobile)) return false;
		String[] ms = mobile.split(",");
		Pattern phonePattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		for(String m: ms) {
			Matcher mch = phonePattern.matcher(m); 
			if(!mch.matches()) return false; 
		}
		return true;
	}
	/**
	 * 根据当前结点编号，当前结点最大子编号。得到下�?��子结点编�?
	 * @param nid 当前结点编号
	 * @param maxChildId 当前结点�?��子编号，如果没有为null
	 * @return 下一个子结点
	 * @throws Exception
	 */
	public static String getNextChildId(String nid,String maxChildId) throws Exception{
		String ncid = null;
		if(maxChildId!=null){
			int nextIntId = Integer.parseInt(maxChildId.substring(maxChildId.length()-3))+1;
			if(nextIntId<10&&nextIntId>0) {
				ncid = nid+"00"+nextIntId;
			}else if(nextIntId>=10&&nextIntId<100) {
				ncid=nid+"0"+nextIntId;
			}else if(nextIntId>=100&&nextIntId<999){
				ncid = nid+nextIntId;
			}else throw new Exception("结点数不在范围内");
		}else {
			ncid = nid+"001";
		}
		return ncid;
	}



	
	public static String getDiffStr(String baseStr,String destStr) {
		String ret = "";
		if(baseStr == null) {
			baseStr = "";
		}
		String[] baseStrs = baseStr.split(",");
		List<String> baseList = Arrays.asList(baseStrs);
		String[] destStrs = destStr.split(",");
		for (String dest : destStrs) {
			if(baseList.contains(dest)) {
				continue;
			} else {
				ret += dest + ",";
			}
		}
		if(ret.contains(",")) {
			return ret.substring(0,ret.length()-1);
		}else {
			return "";
		}
	}
	
	public static int parseInt(String val) {
		if(val==null) {
			return 0;
		} else {
			return Integer.parseInt(val);
		}
	}
	
	
	public static int getDaysBetween(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar d1 = Calendar.getInstance();
		d1.setTime(sdf.parse(date1));
		Calendar d2 = Calendar.getInstance();
		d2.setTime(sdf.parse(date2));
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}
	
	
	public static void main(String[] args) {
		try {
			//System.out.println(getNextChildId("001","001100"));
			//System.out.println(getDiffStr("1/2,1/3","1/1,1/2,1/3,1/4,1/5,1/6,1/7,1/8"));
			System.out.println(getDaysBetween("2015-06-20 00:00:00", "2017-07-01 00:00:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
