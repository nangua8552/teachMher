<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%
	String path = request.getContextPath();
	String basePath=request.getScheme()+"://"
	+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

   <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	padding: 20px;
}
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #ffffff; POSITION: relative; TOP: 2px 
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #FFCC00; POSITION: relative; TOP: 2px
}
</style>
</head>
<body>
<form action="ClasseServlet"  method="post">
			<input type="hidden" name="op" value="add"/>
			班名：<input type="text" name="name"/><br>
			学院：<input type="text" name="college"/><br>
			导员：<select  name="tid">
					<c:forEach items="${tlist }" var="t">
						<option value="${t.id }" >${t.name }</option>
					</c:forEach>								
					</select>
					<br>
			<input type="submit" value="添加"/><br>
</form>
</body>
</html>