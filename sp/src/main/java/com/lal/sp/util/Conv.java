package com.lal.sp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 武瑞暄
 * 系统常量类
 */
public class Conv {
	//系统名称
	public static String APP_NAME = "郑州自来水移动信息化平台";
	//系统会话标记
	public static String SESSION_USER = "session_user";
	
	public static String WS_USER_QUERY="http://128.2.121.109/CSMSWebservice/ChaXunFX/WSChaXunFX.asmx";
	
	public static String LOGIN_URL="http://passport.zzwater.com/Account/GetUserIdentity?returnurl=http://m.zzwater.com:8080/zzwater/logincenter.jsp";
	
	public static String UPLOAD_DOC="upload/DOC/";
	
	public static final String UPLOAD_WORKFLOW="upload/workflow/";
	//1指定人 2指定部门 3指定部门加权限组
	public static final String ASSIGNEE = "1";
	
	public static final String GROUPID = "2";
	
	public static final String GROUPAUTH = "3";
	
	public static final int pageSize = 8;
	
	public static final String NetPortEquType = "交换机,路由器";
	
	//public static final String NetPortType = "万兆光口,千兆光口,千兆电口,百兆电口,十兆口";
	
	public static final Map<Integer,String> NetPortType = new HashMap<Integer, String>();

	public static final Map<Integer,String> EquipmentType = new HashMap<Integer, String>();
	
	public static final Map<Integer,String> TableUse = new HashMap<Integer, String>();
	

	static {
		NetPortType.put(1, "万兆光口");
		NetPortType.put(2, "万兆电口");
		NetPortType.put(3, "千兆光口");
		NetPortType.put(4, "千兆电口");
		NetPortType.put(5, "百兆光口");
		NetPortType.put(6, "百兆电口");
		NetPortType.put(7, "十兆光口");
		NetPortType.put(8, "十兆电口");
		
		EquipmentType.put(1, "路由器");
		EquipmentType.put(2, "交换机");
		
		TableUse.put(1, "大客户");
		TableUse.put(2, "主机托管");
		TableUse.put(3, "虚拟主机");
		TableUse.put(4, "大交换机");
		TableUse.put(5, "路由器");
		TableUse.put(6, "自用");
		TableUse.put(7, "其他");
	}
	
	
}

