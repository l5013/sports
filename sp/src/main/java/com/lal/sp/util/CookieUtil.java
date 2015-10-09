package com.lal.sp.util;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	public static String getCookieValue(String name, HttpServletRequest request){
        try {
            if(name == null)
                return "";
            String allCookieStr = request.getHeader("Cookie");
            if (allCookieStr != null) {
                name = name+"=";
                int begin = allCookieStr.indexOf(name);
                if (begin >= 0) {
                    int end = allCookieStr.indexOf(";", begin);
                    if (end >= 0) {
                        return allCookieStr.substring(begin + name.length(), end);
                    }else{
                        int fromIndex=0;
						return allCookieStr.substring(fromIndex + name.length());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
}
}
