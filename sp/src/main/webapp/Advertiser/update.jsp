<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(function(){
		$("#msform").validation();
	});
</script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7bc0228b6a18c040ba8ea1a06372832"></script>

<style>
	#container {width:400px; height: 260px; } 
</style>

<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="${pageContext.request.contextPath }/Advertiser/list.do">广告商列表</a></li>
  <li class="active">广告商修改页面</li>
</ol>

<div class="row">
		<div class="col-lg-11">
			<form id="msform" class="form-horizontal" method="post" enctype="multipart/form-data">
			
				<fieldset>
					<input type="hidden" value="${advertiser.id }"/>
				  <div class="form-group">
				    <label for="title" class="col-sm-2 control-label">标题</label>
				    <div class="col-sm-5">
				      <input type="text" name="title" value="${advertiser.title }" class="form-control" id="title" placeholder="标题"  btvd-type="required" btvd-class='btvdclass' 	btvd-err="此项必填！">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="file" class="col-sm-2 control-label">图片</label>
				    <div class="col-sm-5">
				       <input type="file" name="file" id="file"> 
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="content" class="col-sm-2 control-label">内容</label>
				    <div class="col-sm-5">
				      <textarea rows="3" cols="40%" name="content" id="content">${advertiser.content }</textarea>
				    </div>
				  </div>
		       	   <div class="form-group">
				    <div class="col-sm-5">
				      <input type="submit" class="action-button" value="保存"/>
				    </div>
				  </div>
				 </fieldset>
			</form>
        </div>
       
</div>
