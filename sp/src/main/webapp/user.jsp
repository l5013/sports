<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function(){
		$("#page").pageView({
			url:"${pageContext.request.contextPath}/Test/jump.do",
			currentPage:"${currentPage}"
		});
	});
</script>
<h2>Contextual Classes</h2>
<div class="table-responsive">
	<table
		class="table table-bordered table-hover table-striped tablesorter">
		<thead>
			<tr>
				<th>Page <i class="fa fa-sort"></i></th>
				<th>Visits <i class="fa fa-sort"></i></th>
				<th>% New Visits <i class="fa fa-sort"></i></th>
				<th>Revenue <i class="fa fa-sort"></i></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.object}" var="u" varStatus="status">
				<c:if test="${status.index%2==0}">
					<tr class="success">
						<td>${u.name}</td>
						<td>${u.adress}</td>
						<td>${u.mobileNumber}</td>
						<td>${u.email}</td>
					</tr>
				</c:if>
				<c:if test="${status.index%2==1}">
					<tr class="warning">
						<td>${u.name}</td>
						<td>${u.adress}</td>
						<td>${u.mobileNumber}</td>
						<td>${u.email}</td>
					</tr>
				</c:if>

			</c:forEach>
		</tbody>
	</table>
	
	<div id="page"/>
</div>
<script type="text/javascript">
	
	function init() {
		//alert(${pageContext.request.contextPath});
		var path = "/ltidc/Test/jump3.do";
		$.post(path, {
			ids : "test"
		}, function(data, status) {
			//var item =  eval(data)[0];
			//alert(item.name);
			var userList = JSON.parse(data);
			for ( var obj in userList) {
				//alert(userList[obj].adress);
			}

		});
	}
</script>