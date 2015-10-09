<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(function(){
		$("#msform").validation();
		var map = new AMap.Map('container');
	    map.setZoom(10);
	    var marker = new AMap.Marker();
	    var lnglatXY = ['${place.longitude}','${place.latitude}'];
    	marker.setPosition(lnglatXY);
    	marker.setMap(map);
	    var clickEventListener = map.on( 'click', function(e) {
	    	marker.setMap();
	    	/* marker = new AMap.Marker({
	    	    position: [e.lnglat.getLng(),e.lnglat.getLat()],
	    	  //  map: map
	    	}); */
	    	var lnglatXY = [e.lnglat.getLng(),e.lnglat.getLat()];
            $("#longitude").val(e.lnglat.getLng());
            $("#latitude").val(e.lnglat.getLat());
	    	marker.setPosition(lnglatXY);
	    	marker.setMap(map);
	    	var MGeocoder;
	        //加载地理编码插件
	        AMap.service(["AMap.Geocoder"], function() {
	            MGeocoder = new AMap.Geocoder({
	                radius: 1000,
	                extensions: "all"
	            });
	            //逆地理编码
	            MGeocoder.getAddress(lnglatXY, function(status, result) {
	                if (status === 'complete' && result.info === 'OK') {
	                    //geocoder_CallBack(result);
	                   // alert(result.regeocode.formattedAddress);
	                    $("#address").val(result.regeocode.formattedAddress);
	                }
	            });
	        });
	    });
	});
	
	var record = { num : "" };
	function checkDecimal(n) {
		var decimalReg = /^\d{0,8}\.{0,1}(\d{1,2})?$/;// var
														// decimalReg=/^[-\+]?\d{0,8}\.{0,1}(\d{1,2})?$/;
		if (n.value != "" && decimalReg.test(n.value)) {
			record.num = n.value;
		} else {
			if (n.value != "") {
				n.value = record.num;
			}
		}
	}
	 
</script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7bc0228b6a18c040ba8ea1a06372832"></script>

<style>
	#container {width:400px; height: 260px; } 
</style>

<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="${pageContext.request.contextPath }/Place/list.do">场地资源列表</a></li>
  <li class="active">场地修改页面</li>
</ol>

<div class="row">
		<div class="col-lg-11">
			<form id="msform" class="form-horizontal" method="post" enctype="multipart/form-data">
			
				<fieldset>
					<input type="hidden" value="${place.id }"/>
				  <div class="form-group">
				    <label for="title" class="col-sm-2 control-label">标题</label>
				    <div class="col-sm-5">
				      <input type="text" name="title" value="${place.title }" class="form-control" id="title" placeholder="标题"  btvd-type="required" btvd-class='btvdclass' 	btvd-err="此项必填！">
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
				      <textarea rows="3" cols="40%" name="content" id="content">${place.content }</textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="openTime" class="col-sm-2 control-label">开放时间</label>
				    <div class="col-sm-5">
				      <input type="text" value="${place.openTime }" name="openTime" class="form-control datepicker" placeholder="开放时间">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="closeTime" class="col-sm-2 control-label">关闭时间</label>
				    <div class="col-sm-5">
				      <input type="text" name="closeTime" value="${place.closeTime }" class="form-control datepicker" placeholder="关闭时间">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="address" class="col-sm-2 control-label">地址</label>
				    <div id="container" class="col-sm-5"></div> 
				  </div>
				  <div class="form-group">
				    <label for="address" class="col-sm-2 control-label"></label>
				    <div class="col-sm-5">
				      <input type="text" name="address" class="form-control" id="address" readonly="readonly" value="${place.address }">
				      <input type="hidden" name="longitude" id="longitude" value="${place.longitude }">
				      <input type="hidden" name="latitude" id="latitude" value="${place.latitude }">
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="sportsCategory" class="col-sm-2 control-label">类型</label>
				    <div class="col-sm-5">
				      <select class="form-control" btvd-type="selected" btvd-class='btvdclass' btvd-err="请选择场地类型！" id="sportsCategory" name="sportsCategory.id">
					  <option value="-1">请选择</option>
					  <c:forEach items="${sportsCategory }" var="category">
					  	<option value="${category.id }" <c:if test="${category.id == place.sportsCategory.id }">selected="selected"</c:if>>${category.name }</option>
					  </c:forEach>
				  </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="price" class="col-sm-2 control-label">价格</label>
				    <div class="col-sm-5">
					    <div class="input-group">
					      <div class="input-group-addon">¥</div>
					      <input type="text" name="price" value="${place.price }" class="form-control" id="price" placeholder="价格" onkeyup="checkDecimal(this)">
					    </div>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="quantity" class="col-sm-2 control-label">数量</label>
				    <div class="col-sm-5">
				      <input type="text" name="quantity" value="${place.quantity }" class="form-control" id="quantity" placeholder="数量" btvd-type="required number" btvd-class='btvdclass'>
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
