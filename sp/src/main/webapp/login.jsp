<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String msg = (String)request.getAttribute("msg");
%>
<c:set scope="page" var="msg" value="<%=msg %>"></c:set>

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8"/>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>联通IDC管理系统登录</title>
        <!-- 最新 Bootstrap 核心 CSS 文件 -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<style type="text/css">
			body {
			  padding-bottom: 40px;
			  background-color: #333;
			}
			
			.form-signin {
			  max-width: 330px;
			  padding: 15px;
			  margin: 0 auto;
			  margin-top: 20px;
			  z-index: 999;
			}
			.form-signin .checkbox {
			  margin-bottom: 10px;
			}
			.form-signin .checkbox {
			  font-weight: normal;
			}
			.form-signin .form-control {
			  position: relative;
			  font-size: 16px;
			  height: auto;
			  padding: 10px;
			  -webkit-box-sizing: border-box;
			     -moz-box-sizing: border-box;
			          box-sizing: border-box;
			}
			.form-signin .form-control:focus {
			  z-index: 2;
			}
			.form-signin input[type="text"] {
			  margin-bottom: -1px;
			  border-bottom-left-radius: 0;
			  border-bottom-right-radius: 0;
			}
			.form-signin input[type="password"] {
			  margin-bottom: 10px;
			  border-top-left-radius: 0;
			  border-top-right-radius: 0;
			}
			#login-logo-div {
			  background-color: #FFF;
			  margin: 0;
			  padding: 0;
			  height: 300px;
			  width: 100%;
			}
		</style>
    </head>
    <body>
    	<div id="login-logo-div">
    		<div style="margin:0 auto;width: 500px;">
	    		<img src="${pageContext.request.contextPath }/images/logo22.jpg" alt="logo" style="margin-top:150px;"/>
    		</div>
    	</div>
	    <div class="container">
	      <form class="form-signin" role="form" action="${pageContext.request.contextPath }/Auth/login.do" method="post">
	        <input type="text" class="form-control" placeholder="用户名" name="username" required autofocus />
	        <br/>
	        <input type="password" class="form-control" placeholder="密码" name="password" required />
	        <input type="hidden" name="authTag" value="admin"/>
	        <button class="btn btn-lg btn-default btn-block" type="submit"><font style="font-weight: bolder;">登&nbsp;&nbsp;录</font></button>
	      </form>
	    </div>
	    <c:if test="${not empty pageScope.msg }">
		    <div style="width: 330px;margin: 0 auto;" class="alert alert-danger">${pageScope.msg }</div>
	    </c:if>
	</body>
</html>
