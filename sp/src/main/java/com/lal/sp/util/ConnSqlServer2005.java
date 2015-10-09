package com.lal.sp.util;

import java.sql.*;

public class ConnSqlServer2005 {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ConnSqlServer2005() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// 1295
			String url = "jdbc:sqlserver://27.50.129.138:1433;user=sa;password=xinxiangchengtou2012";
			con = DriverManager.getConnection(url);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String str = "select *  from [SmsDb].[dbo].T_SendTask";//[ctfcpm].[dbo].[Finance_LoanRecord]
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
			/*
			 * rs = stmt.executeQuery("select max(id) from news"); rs.next();
			 * int maxId = rs.getInt(1); maxId++;
			 * System.out.println("maxId:"+maxId); String insertSql =
			 * "insert into news(id,title,content)values("
			 * +maxId+",'新闻标题','新闻内容')"; boolean go = stmt.execute(insertSql);
			 * System.out.println("go="+go);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (con != null) {
			con.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

	public static void main(String[] args) throws SQLException {
		ConnSqlServer2005 conn2005 = new ConnSqlServer2005();
	}
}
