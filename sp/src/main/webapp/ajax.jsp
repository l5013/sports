<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: 'POST', 
        data: JSON.stringify({"id":2,"name":"李四","nickname":"ls","picture":null,"phone":"13595363999","password":"123456","age":21,"gender":"女","email":"ls@ls.com","qq":"703215213","weixin":null,"interest":null,"introduction":null,"province":"1","city":"3","district":"0","job":null,"jifen":null,"status":null,"remark":null,"sportsCategory":[{"id":5,"code":"d56a5b895ae44520aef910f037878693","name":"排球","status":1,"remark":null},{"id":6,"code":"aa733d304f454ddbb362b3db7b9e6de8","name":"篮球","status":1,"remark":null},{"id":7,"code":"c7538c30f0e24bbea0f326ca24554f0a","name":"羽毛球","status":1,"remark":null}]}), 
        dataType: 'json',
        contentType:'application/json;charset=UTF-8',        
        success: function(result) { 
            alert(result.success); 
        },
        url:'${pageContext.request.contextPath}/User/register.do'
	});
});
</script>
</head>
<body>

</body>
</html>