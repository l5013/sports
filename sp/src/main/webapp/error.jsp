<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type"
			content="application/xhtml+xml; charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="-1" />
		<title>500 -错误</title>
	</head>
	<body>
		<%int status_code = 0;
			if(null!=request.getAttribute("javax.servlet.error.status_code")){
				status_code=((Integer) request.getAttribute("javax.servlet.error.status_code"));
			}
		 %>
		 <p>
		<font color="red">${msg }</font>
		</p>
		<hr>
		<font color=#0000FF>
		<font color="#0000FF">错误信息为:<%=exception==null?"":exception.getMessage()%> <%--根据配置文件或url参数决定是否显示--%>
		<% if(status_code==404) {%>
			<font color="red">您访问的页面没有找到！错误代码：<%=status_code %></font>
		<%} %>
		<%
			String sIsDebug = request.getParameter("debug");
			if (sIsDebug == null || sIsDebug.equals(""))
				sIsDebug = "false";
		%>
		<hr>
			
			错误详细信息(供开发人员调试):<%=exception==null?"":exception.toString()%> <br/>
			<hr>
			问题如下(供开发人员调试)：
			<pre>
			<% if(exception!=null){exception.printStackTrace(new java.io.PrintWriter(out));} %>
			</pre>
			
				<hr/>
				
				 <hr/>
				<a href='javascript:history.go(-1);'>返回上页</a>
		</font></font>
	</body>
</html>