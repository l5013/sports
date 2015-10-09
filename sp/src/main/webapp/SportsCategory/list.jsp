<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/tagscloud.js"></script>
<script type="text/javascript">
	function deleteCategory(obj) {
		window.location.href=$(obj).attr("url");
	}
</script>
<style type="text/css">
body{ font-family:"微软雅黑", Arial, sans-serif;} #main{border:none; background:none;}
body,ul,li,h1,h2,h3,p,form{margin:0;padding:0;}body{background:#fbfbfb;color:#444;font-size:14px;}
a{color:#444;text-decoration:none;}a:hover{color:red;}

/* tagscloud */
#tagscloud{width:250px;height:260px;position:relative;font-size:12px;color:#333;margin:20px auto 0;text-align:center;}
#tagscloud a{position:absolute;top:0px;left:0px;color:#333;font-family:Arial;text-decoration:none;margin:0 10px 15px 0;line-height:18px;text-align:center;font-size:12px;padding:1px 5px;display:inline-block;border-radius:3px;}
#tagscloud a.tagc1{background:#666;color:#fff;}
#tagscloud a.tagc2{background:#F16E50;color:#fff;}
#tagscloud a.tagc3{background:#006633;color:#fff;}
#tagscloud a:hover{color:#fff;background:#0099ff;}
#tagscloud a span {
	color:#000;
}
#tagscloud a span:hover {
	color:#D2D2D2;
}
</style>
<div id="tagscloud">
	<c:forEach items="${records }" var="category" varStatus="status">
	
		<a href="#" class="tagc${status.index%3+1}" title="${category.name }">${category.name }&nbsp;&nbsp;&nbsp;<span url="${pageContext.request.contextPath }/SportsCategory/delete/${category.id }.do" class="glyphicon glyphicon-remove" onclick="deleteCategory(this)"></span></a>
		
	</c:forEach>
</div>

<div class="col-lg-5" style="position:relative;left:300px;">
	<form id="msform" class="form-horizontal" action="${pageContext.request.contextPath }/SportsCategory/add.do" method="post">
	 <div class="input-group" style="margin-bottom: 8px;">
            	<input type="text" class="form-control" name="name" value="${searchInfo }" placeholder="请输入名称" >
				      <span class="input-group-btn">
			          <button class="btn btn-default" type="submit">
			          	<span class="glyphicon glyphicon-plus"></span> 添加
			          </button>
			        </span>
			</div>
	</form>
</div>
