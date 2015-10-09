package com.lal.sp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DateSynchronousUtil {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		saveYX_ZY();	//院系
//		insertZY();		//专业
//		insertBJ();		//班级
		
		insertStudent();		//录入学生
		insertTeacher();		//录入教师
	}
	/**
	 * 录入院系
	 */
	public static void saveYX_ZY(){
		try {
	        Connection conn = getOracle();
	        Statement st=conn.createStatement();
	        ResultSet result=st.executeQuery("select dwdm,dwbzmc,dwcc,dwlbm from t_zxbz_dw  order by px");
	        
	        Connection mysql=getMysqlConnection();
	        mysql.setAutoCommit(false);
	        Statement stmt; //创建声明
            stmt = mysql.createStatement();
            
            mysql.createStatement().executeUpdate("DELETE FROM zsxy_node");
            mysql.createStatement().executeUpdate("INSERT INTO zsxy.zsxy_node (id, leaf, node_pid, node_type, qtip, TEXT)VALUES('101', 'true', '10', 'xn1', '院系', '院系')");
            mysql.createStatement().executeUpdate("INSERT INTO zsxy.zsxy_node (id, leaf, node_pid, node_type, qtip, TEXT)VALUES('102', 'true', '10', 'xn1', '校方机构', '校方机构')");
            
	        while(result.next()) {
	        	System.out.println("dwdm:" + result.getString("dwdm"));
	        	System.out.println("dwbzmc:" + result.getString("dwbzmc"));
	        	String pid="102";
	        	String type="xn3";
	        	if("1".equals(result.getString("dwcc"))&&"03".equals(result.getString("dwlbm"))){	//如果是院系
	        		pid="101";
	        		type="xn2";
	        	}
	        	String insert="INSERT INTO zsxy.zsxy_node 	(id,leaf,node_pid,node_type,qtip,TEXT)VALUES"+
	"('10"+result.getString("dwdm")+"', 'true','"+pid+"','"+type+"','"+result.getString("dwbzmc")+"','"+result.getString("dwbzmc")+"')";
	        	
	        	stmt.executeUpdate(insert);
	        }
	        
	        mysql.commit();  
	        mysql.close();
	        
	        result.close();
	        st.close();
	        conn.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * 录入专业
	 */
	public static void insertZY(){
		try {
	        Connection conn = getOracle();
	        Statement st=conn.createStatement();
	        ResultSet result=st.executeQuery("select dwdm,zydm,zymc from t_zxbz_bzkzy order by px");
	        
	        Connection mysql=getMysqlConnection();
	        mysql.setAutoCommit(false);
	        Statement stmt; //创建声明
            stmt = mysql.createStatement();
	        while(result.next()) {
	        	String insert="INSERT INTO zsxy.zsxy_node 	(id,leaf,node_pid,node_type,qtip,TEXT)VALUES"+
	"('10"+result.getString("zydm")+"', 'true','10"+result.getString("dwdm")+"','xn4','"+result.getString("zymc")+"','"+result.getString("zymc")+"')";
	        	stmt.executeUpdate(insert);
	        }
	        
	        mysql.commit();  
	        mysql.close();
	        
	        result.close();
	        st.close();
	        conn.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * 录入班级
	 */
	public static void insertBJ(){
		try {
	        Connection conn = getOracle();
	        Statement st=conn.createStatement();
	        ResultSet result=st.executeQuery("select bjdm,bjmc,yxsh,zydm from t_jbxx_bj");
	        
	        Connection mysql=getMysqlConnection();
	        mysql.setAutoCommit(false);
	        Statement stmt; //创建声明
            stmt = mysql.createStatement();
	        while(result.next()) {
	        	String insert="INSERT INTO zsxy.zsxy_node 	(id,leaf,node_pid,node_type,qtip,TEXT)VALUES"+
	        			"('10"+result.getString("bjdm")+"', 'true','10"+result.getString("zydm")+"','xn5','"+result.getString("bjmc")+"','"+
	        			result.getString("bjmc")+"')";
	        	stmt.executeUpdate(insert);
	        }
	        
	        mysql.commit();  
	        mysql.close();
	        
	        result.close();
	        st.close();
	        conn.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * 录入学生
	 */
	public static void insertStudent() {
		int count=0;
	    String sql="SELECT xh,xm,mzdm,xbdm,sfzjh,yxsh,bjdm,zydm,xz,xznj,jynf,rxrq from T_BYS_JBXX u" ;
	    try {
	        Connection conn = getOracle();
	        Statement st=conn.createStatement();
	        ResultSet result=st.executeQuery(sql);
	        
	        Connection mysql=getMysqlConnection();
	        mysql.setAutoCommit(false);
	        Statement stmt = null; //创建声明
	        stmt = mysql.createStatement();
	        Map<String,String> nodes=new HashMap<String,String>();
	        ResultSet rs=stmt.executeQuery("select id,text from zsxy_node");
	        while(rs.next()) {
	        	nodes.put(rs.getString("id"), rs.getString("text"));
	        }
	        
	        while(result.next()) {
	        	System.out.println("xh:" + result.getString("xh"));
	            System.out.println("xm:" + result.getString("xm"));
	            //insert user
        String insert="INSERT INTO zsxy.zsxy_user (user_uid,user_auth,user_birthday,user_desc,user_email,user_mobileoperator,user_name,"+ 
        		"user_nation,user_nativeplace,user_password,user_phone,user_politicalstatus,user_scard,user_school,user_sex,user_sname,"+ 
        		"user_state,user_type,user_peie,user_managenode,user_sfrzh)VALUES('"+result.getString("xh")+"','TT',null,'',"+
        		"'','','"+result.getString("xm")+"','"+result.getString("mzdm")+"','',null,'','', "+
        		"'','zzili','"+changeSex(result.getString("xbdm"))+"','','正常','学生',null,null,"+result.getString("xh")+")";
        
        		stmt.executeUpdate(insert);
        		//insert student
        	    String student="INSERT INTO zsxy.zsxy_student (uid,student_class,student_college,student_enrollmentdate,student_flat,student_grade,"+ 
        		"student_room,student_specialty,student_system)VALUES('"+result.getString("xh")+"','"+nodes.get("10"+result.getString("bjdm"))+"','"+nodes.get("10"+result.getString("yxsh"))+
        		"','"+changeRXRQ(result.getString("rxrq"),result.getString("xznj"),result.getString("jynf"),result.getString("xz"))+"',null, "+"'"+result.getString("xznj")+"级', null,'"+nodes.get("10"+result.getString("zydm"))+"','"+result.getString("xz")+"')";
        	    stmt.executeUpdate(student);
        	    //insert usernode
        	    System.out.println("nid=10"+result.getString("yxsh")+result.getString("zydm")+result.getString("bjdm"));
        	    String usernode="";
        	    if(Tools.isNotEmpty(result.getString("bjdm"))){
        	    	usernode="INSERT INTO zsxy.zsxy_usernode(description,roles,nid,uid)VALUES" +
            	    		"('学生','收件人','10"+result.getString("bjdm")+"','"+result.getString("xh")+"')";
        	    }else{
        	    	usernode="INSERT INTO zsxy.zsxy_usernode(description,roles,nid,uid)VALUES" +
            	    		"('学生','收件人','10"+result.getString("zydm")+"','"+result.getString("xh")+"')";
        	    }
        	    
        	    stmt.executeUpdate(usernode);
        	    count++;
	        }

	    
	        mysql.commit();  
	        mysql.close();
	        
	        System.out.println(count+"学生");
	        
	        result.close();
	        st.close();
	        conn.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertTeacher(){
		String sql="SELECT zgh,xm,csrq,xbdm,mzdm,dwdm from T_jzg_JBXX u" ;
		int count=0;
	    try {
	        Connection conn = getOracle();
	        Statement st=conn.createStatement();
	        ResultSet result=st.executeQuery(sql);
	        
	        Connection mysql=getMysqlConnection();
	        mysql.setAutoCommit(false);
	        Statement stmt = null; //创建声明
	        stmt = mysql.createStatement();
	        Map<String,String> nodes=new HashMap<String,String>();
	        ResultSet rs=stmt.executeQuery("select id,text from zsxy_node");
	        while(rs.next()) {
	        	nodes.put(rs.getString("id"), rs.getString("text"));
	        }
	        
	        while(result.next()) {
	        	System.out.println("zgh" + result.getString("zgh"));
	            System.out.println("xm:" + result.getString("xm"));
	            //insert user
        String insert="INSERT INTO zsxy.zsxy_user (user_uid,user_auth,user_birthday,user_desc,user_email,user_mobileoperator,user_name,"+ 
        		"user_nation,user_nativeplace,user_password,user_phone,user_politicalstatus,user_scard,user_school,user_sex,user_sname,"+ 
        		"user_state,user_type,user_peie,user_managenode,user_sfrzh)VALUES('"+result.getString("zgh")+"','TT',"+changeCSRQ(result.getString("csrq"))+",'',"+
        		"'','','"+result.getString("xm")+"','"+result.getString("mzdm")+"','',null,'123456','', "+
        		"'','zzili','"+changeSex(result.getString("xbdm"))+"','','正常','教职工',null,null,'"+result.getString("zgh")+"')";
        
        		stmt.executeUpdate(insert);
        		//insert student
        	    String student="INSERT INTO zsxy.zsxy_teacher (uid,teacher_degree,teacher_education,teacher_office,teacher_title,teacher_worktel,"+ 
	"teacher_level)VALUES('"+result.getString("zgh")+"','','','','','','')";
        	    stmt.executeUpdate(student);
        	    //insert usernode
        	    System.out.println("nid=10"+result.getString("dwdm"));
        	    String usernode="INSERT INTO zsxy.zsxy_usernode(description,roles,nid,uid)VALUES" +
        	    		"('教职工','收件人','10"+result.getString("dwdm")+"','"+result.getString("zgh")+"')";
        	    stmt.executeUpdate(usernode);
        	    
	        }

	    
	        mysql.commit();  
	        mysql.close();
	        
	        System.out.println(count+"教师");
	        
	        result.close();
	        st.close();
	        conn.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * 转换性别
	 * @param sex
	 * @return
	 */
	public static String changeSex(String sex){
		if("1".equals(sex)){
			return "男";
		}else{
			return "女";
		}
	}
	public static String changeCSRQ(String csrq){
		if(Tools.isEmpty(csrq)){
			return "null";
		}else{
			return "'"+csrq+"'";
		}
	}
	/**
	 * 转换入学日期
	 * @param sex
	 * @return
	 */
	public static String changeRXRQ(String rxrq,String xznj,String jynf,String xz){
		if(Tools.isNotEmpty(rxrq)&&rxrq.length()==8){
			return rxrq.substring(0, 4)+"-"+rxrq.substring(4,6)+"-"+rxrq.substring(6,8);
		}else{
			if(Tools.isNotEmpty(xznj)){
				return xznj+"-09-15";
			}else{
				int y=Integer.parseInt(jynf);
				int x=Integer.parseInt(xz);
				return (y-x)+"-09-15";
			}
			
		}
	}
	public static Connection getMysqlConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/zsxy?useUnicode=true&amp;characterEncoding=utf-8", "xfjx", "xfjx4561");
        return con;
	}
	
	public static Connection getOracle() throws Exception{
		InputStream in=DateSynchronousUtil.class.getResourceAsStream("/db.properties");  
		Properties prop = new Properties(); 
		try {
		prop.load(in);
		} catch (IOException e) {  
		e.printStackTrace();  
		}  
		 //通过Map方式设置Druid参数
        Map<String, String> druidMap=new HashMap<String, String>();
        druidMap.put(DruidDataSourceFactory.PROP_USERNAME, prop.getProperty("user", ""));
        druidMap.put(DruidDataSourceFactory.PROP_PASSWORD, prop.getProperty("password", ""));
        druidMap.put(DruidDataSourceFactory.PROP_URL, prop.getProperty("jdbcUrl", ""));
        druidMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, "");
        //通过DruidDataSourceFactory获取DataSource实例
        DataSource dataSource = DruidDataSourceFactory.createDataSource(druidMap);
        return dataSource.getConnection();
	}
}
