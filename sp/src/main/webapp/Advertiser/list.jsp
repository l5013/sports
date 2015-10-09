<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(function(){
		$("#page").bootstrapPaginator({
			currentPage: "${pageBean.currentPage}",
			totalPages: "${pageBean.pageCount}",
			numberOfPages:"${pageBean.pageSize}",
			pageUrl:function(type, page, current) {
				return "${pageContext.request.contextPath}/Advertiser/list.do?currentpage="+page;
			}
		});
	});
	
	function showModel(obj) {
		//alert($(obj).attr("href"));
		$("#imgModal").modal("show").on("shown.bs.modal",function(e){
			$("#imgSrc").attr("src","${pageContext.request.contextPath}/"+$(obj).attr("href"));
		});
	}
</script>
<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li class="active">广告商列表</li>
</ol>

<div class="row">
		<div class="col-lg-12">
			<a href="${pageContext.request.contextPath }/Advertiser/add.do" style="text-decoration: none;float: right;"><h4><span class="label label-default">添加广告商</span></h4></a>
		</div>
          <div class="col-lg-12">
            <h2>广告商列表</h2>
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-striped listTable">
                <thead>
                  <tr>
                    <th>标题</th>
                    <th>图片</th>
                    <th>内容</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                 <c:forEach items="${pageBean.recordList }" var="advertiser">
                 	<tr>
			        <td>${advertiser.title}</td>
			        <td><a href="${advertiser.pic }" onclick="showModel(this);return false;" style="text-decoration: none;"><span class="label label-default">查看图像</span></a></td>
			        <td>${advertiser.content}</td>
			        <td><a href="${pageContext.request.contextPath }/Advertiser/update/${advertiser.id }.do" style="text-decoration: none;"><span class="label label-default">修改</span></a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" url="${pageContext.request.contextPath }/Advertiser/delete/${advertiser.id }.do" style="text-decoration: none;"  class="delete"><span class="label label-default">删除</span></a></td>
			      	</tr>
			     </c:forEach>
                </tbody>
              </table>
              <div id="page" style="float: right;"></div>
            </div>
          </div>
        </div>
        
        
        <!-- Modal -->
 <div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">图像查看</h4>
      </div>
      <div class="modal-body">
      	<img src="" class="img-responsive" alt="Responsive image" id="imgSrc">
      </div>
    </div>
  </div>
</div>
