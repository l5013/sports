<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.lal.sp.util.Conv,com.lal.sp.util.Tools,com.lal.sp.bean.BigCusBusi,com.lal.sp.bean.MachineManaBusi,com.lal.sp.bean.VirMachineBusi,com.lal.sp.bean.DomainRegBusi,com.lal.sp.bean.WebsiteBusi" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css"rel="stylesheet">
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bt-validate.js"></script>
		<script type="text/javascript">
			$(function(){
		 		$("#netportForm").validation();
		 		 
		 		$("#macul").on("click","li a",function(event){
					if($("#mac").val() == "") {
						$("#mac").val($(this).html());
					}else {
						$("#mac").val($("#mac").val()+","+$(this).html());
					}
				});
		 		
		 		 $("#netportres tbody tr", parent.document).on("click","a",function(event){
		 			var that = $(this);
		 			$.ajax({
		                cache: true,
		                type: "get",
		                url:"${pageContext.request.contextPath }/BigCusBusi/deleteNetport.do",
		                data:{net_id:that.attr("net_id")},
		                async: false,
		                error: function(request) {
		                    alert("Connection error");
		                },
		                success: function(data) {
		                	if(data=="success")
		                		that.parent().parent().remove();
		                }
		            });
		 		 });
			});
			
			function chooseNpType(obj) {
				if(obj.value != -1) {
					$("#port_type option[value !='-1']").remove();
					$("#macul li").remove();
					$.ajax({
						url:"${pageContext.request.contextPath}/Customer/getNpType.do",
						data: {npName:obj.value},
						success:function(data) {
							$.each(data,function(index) {
								$("#port_type").append("<option value='"+index+"'>"+data[index]+"</option>");
							});
						}
					});
				} else {
					$("#port_type option[value !='-1']").remove();
					$("#macul li").remove();
				}
			}
			
			function chooseMac(obj) {
				if(obj.value != -1) {
					$("#macul li").remove();
					$.ajax({
						url:"${pageContext.request.contextPath}/Customer/getNpMac.do",
						data: {npName:$("#equipment_name").val(),npType:obj.value},
						success:function(data) {
							var leftMacs = data.left_mac.split(",");
							$("#equipment_id").val(data.net_id);
							$("#equipment_type").val(data.equipment_type);
							$.each(leftMacs,function(index,data) {
								$("#macul").append("<li><a href='#'>"+data+"</a></li>");
							});
						}
					});
				} else {
					$("#macul li").remove();
				}
			}
			
			function ajaxSubmit() {
				$.ajax({
	                cache: true,
	                type: "get",
	                url:"${pageContext.request.contextPath }/BigCusBusi/addNetport.do",
	                data:$("#netportForm").serialize(),
	                async: false,
	                error: function(request) {
	                    alert("Connection error");
	                },
	                success: function(data) {
	                	$("#closeBtn", parent.document).click();
	                	$("#netProtModal iframe", parent.document).attr("src","${pageContext.request.contextPath }/Customer/getNetportName.do");
	                	var npTable = $("#netportres", parent.document);
	                	npTable.append("<tr><td>"+data.equipment_name+"</td><td>"+convertEquipmentType(data.equipment_type)+"</td><td>"+convertPortType(data.port_type)+"</td><td>"+data.mac+"</td><td><a href='#' net_id='"+data.net_id+"' onclick='deleteNp(this)' style='text-decoration: none;padding: 5px;'><span class='label label-default'>删除</span></a></td><input type='hidden' name='nids' value='"+data.net_id+"'/></tr>");
	                	npTable.parent().css("display","block");
	                }
	            });
			}
			
			function convertEquipmentType(data) {
				var equipment_type = "";
				if(data=="1"){
					equipment_type = "<%=Conv.EquipmentType.get(Integer.parseInt("1"))%>";
				}
				else if(data=="2") {
					equipment_type = "<%=Conv.EquipmentType.get(Integer.parseInt("2"))%>";
				}
				else {
					equipment_type = "无相关类型";
				}
				return equipment_type;
			}
			
			function convertPortType(data) {
				var port_type = "";
				if(data=="1") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("1")) %>";
				}
				else if(data=="2") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("2")) %>";
				}
				else if(data=="3") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("3")) %>";
				}
				else if(data=="4") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("4")) %>";
				}
				else if(data=="5") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("5")) %>";
				}
				else if(data=="6") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("6")) %>";
				}
				else if(data=="7") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("7")) %>";
				}
				else if(data=="8") {
					port_type = "<%=Conv.NetPortType.get(Integer.parseInt("8")) %>";
				}
				else {
					port_type = "无相关类型";
				}
				return port_type;
			}
			
			function deleteNp(obj) {
				alert(1);
				alert($(obj).attr("net_id").val());
				/* $.ajax({
	                cache: true,
	                type: "get",
	                url:"${pageContext.request.contextPath }/BigCusBusi/addNetport.do",
	                data:{"equipment_id":$("#equipment_id").val(),"equipment_type":$("#equipment_type").val(),"equipment_name":$("#equipment_name").val(),"port_type":$("#port_type").val(),"mac":$("#mac").val()},
	                async: false,
	                error: function(request) {
	                    alert("Connection error");
	                },
	                success: function(data) {
	                }
	            }); */
			}
		</script>
	</head>
	<body>
		<form class="form-horizontal" id="netportForm" action="${pageContext.request.contextPath }/BigCusBusi/addNetport.do" role="form" method="post">
		 	<div class="col-lg-11">
		 		<input type="hidden" name="equipment_id" id="equipment_id"/>
		 		<input type="hidden" name="equipment_type" id="equipment_type"/>
				  <div class="form-group">
				  <label for="equipment_name" class="col-sm-2 control-label">设备名称</label>
				    <div class="col-sm-4">
				    <select class="form-control" btvd-type="selected" btvd-class='btvdclass' btvd-err="请选择设备名称！" id="equipment_name" name="equipment_name" onchange="chooseNpType(this)">
					  <option value="-1">请选择</option>
				    	<c:forEach items="${npNames }" var="npName">
						  <option value="${npName }">${npName }</option>
				    	</c:forEach>
					</select>
				    </div>
				  </div>
				  <div class="form-group">
				  <label for="port_type" class="col-sm-2 control-label">端口类型</label>
				    <div class="col-sm-4">
				    <select class="form-control" btvd-type="selected" btvd-class='btvdclass' btvd-err="请选择端口类型！" id="port_type" name="port_type" onchange="chooseMac(this)">
					  <option value="-1">请选择</option>
					</select>
				    </div>
			  	 </div>
				  <div class="form-group">
					  <label for="mac" class="col-sm-2 control-label">物理地址</label>
					    <div class="col-sm-4">
					  	<div class="input-group">
					    <!-- <select class="form-control" btvd-type="selected" btvd-class='btvdclass' btvd-err="请选择物理地址！" id="mac" name="mac">
						  <option value="-1">请选择</option>
						</select> -->
							<input type="text" class="form-control" placeholder="物理地址" btvd-type="required" btvd-class='btvdclass' btvd-err="请选择物理地址！" id="mac" name="mac">
							<div class="input-group-btn">
					        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">物理地址 <span class="caret"></span></button>
					        <ul class="dropdown-menu pull-right" id="macul" style="overflow: auto;">
					        </ul>
					      </div>
					   	</div>
					   	</div>
			  	 </div>
			  	 <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="button" class="btn btn-default" onclick="ajaxSubmit()">添加</button>
				    </div>
				  </div>
		  	 </div>
		 </form>
	</body>
</html>

