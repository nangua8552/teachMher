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
<form action="StudentServlet"  method="post">
			<input type="hidden" name="op" value="add"/>
			������<input type="text" name="name"/><br>
			���䣺<input type="text" name="age"/><br>
			�Ա�<input type="radio" name="sex" value="1" checked="checked"/>��<input type="radio" name="sex" value="0"/>Ů<br>
			�绰��<input type="text" name="tel"/><br>
			��ַ��<input type="text" name="address"/><br>
			�༶��<select  name="cid">
					<c:forEach items="${clist }" var="c">
						<option value="${c.id }" >${c.name }</option>
					</c:forEach>								
					</select>
					<br>
			<input type="submit" value="���"/><br>
</form>
</body>
</html>