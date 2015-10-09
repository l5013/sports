package com.lal.sp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CharTools {
	
	public static String changeURLToUTF(String c){
		String text=null;
		try {
			text = URLDecoder.decode(c, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return text;
	}
}
