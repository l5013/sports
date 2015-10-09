<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE jsp PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
			<meta name="author" content="">

				<title>新乡联通IDC管理系统-${title}</title> <!-- Bootstrap core CSS -->
				<link href="${pageContext.request.contextPath}/css/bootstrap.css"
					rel="stylesheet">

					<!-- Add custom CSS here -->
					<link href="${pageContext.request.contextPath}/css/sb-admin.css"
						rel="stylesheet">
						<link rel="stylesheet"
							href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
							<!-- Page Specific CSS -->
							<link rel="stylesheet"
								href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui/base/jquery.ui.all.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ltidc.keywordinput.css">
	<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/css/zzsc.css" />
	
	<!-- JavaScript -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

	<!-- Page Specific Plugins -->
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/morris/chart-data-morris.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
		
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bt-validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-ui/jquery-ui-1.10.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/core/jquery.ltidc.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/core/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/core/jquery.ltidc.keywordinput.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#updatePwdForm").validation();
		});		
		
		function checkPwd() {
			var el = $("#repeatPass");
			var controls = el.parent().find('a');
			if($("#repeatPass").val()!=$("#password").val()) {
				el.after("<a style='visibility:hidden;' data-placement='bottom' data-content='两次密码必须一致！！' data-toggle='popover' href='#' >aa</a>");
				el.next().popover("show");
		        var pop=el.parent().find(".popover"),pos=pop.offset();
		        pos.top=pos.top-el.next().height();
		        pop.offset(pos);
			}else {
				controls.popover("hide");
				$("#updatePwdForm").attr("action","${pageContext.request.contextPath }/User/updatePassword/${loginUser.uid}.do?password="+$("#password").val());
				$("#updatePwdForm").submit();
			}
		}
	</script>
</head>

<body>

	<div id="wrapper">

		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top" >
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle
				navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> </button>
			<a class="navbar-brand" href="index.jsp">新乡联通IDC管理系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li><a href="${pageContext.request.contextPath}/SportsCategory/list.do"><i class="fa fa-file"></i> 运动类别管理</a></li>
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i> 资源管理 <b class="caret"></b></a>
	              <ul class="dropdown-menu">
	                <li><a href="${pageContext.request.contextPath }/Place/list.do">场地管理</a></li>
	                <li><a href="${pageContext.request.contextPath }/Insurance/list.do">运动保险管理</a></li>
	                <li><a href="${pageContext.request.contextPath }/Advertiser/list.do">广告商管理</a></li>
	              </ul>
	            </li>
				<li><a href="${pageContext.request.contextPath}/MessagePush/push.do"><i class="fa fa-file"></i> 消息推送</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right navbar-user">
				<li class="dropdown user-dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><i
						class="fa fa-user"></i> ${loginUser.realname }<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#" data-toggle="modal" data-target="#pwdModal"><i class="fa fa-gear"></i>修改密码</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath }/Auth/logout.do"><i class="fa fa-power-off"></i>注销</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>
		<div id="page-wrapper">
		

			<div class="row">
				<div class="col-lg-12">
		 			<c:set scope="page" var="page" value="${pagepath}"></c:set>
					<c:if test="${empty page}">
      	首页信息
      </c:if>
					<c:if test="${not empty page}">
						<jsp:include page="${page}" flush="true">
							<jsp:param value="${pageCount}" name="pageCount" />
							<jsp:param value="${param2}" name="param2" />
						</jsp:include>
						<c:set scope="request" value="${object}" var="object" />
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
	<!-- /#wrapper -->
	<div id="dialog-confirm">
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">更改密码</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal submitForm" role="form" id="updatePwdForm">
        	<div class="form-group">
			  <label for="password" class="col-sm-2 control-label">新密码</label>
			    <div class="col-sm-5">
			      <input type="password" name="password" class="form-control" id="password" placeholder="新密码"  btvd-type="required" btvd-class='btvdclass' btvd-err="此项必填！">
			    </div>
		    </div>
        	<div class="form-group">
			  <label for="repeatPass" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-5">
			      <input type="password" name="repeatPass" class="form-control" id="repeatPass" placeholder="确认密码"  btvd-type="required" btvd-class='btvdclass' btvd-err="此项必填！">
			    </div>
		    </div>
		    <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="button" class="btn btn-default" onclick="checkPwd()">修改</button>
			    </div>
		   </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
