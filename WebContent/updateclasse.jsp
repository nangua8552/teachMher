<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ClasseServlet"  method="post">
		<input type="hidden" name="op"value="update"/>
		<input type="hidden" name="id"value="${c.id}"/>
		班名：<input type="text" name="name" value="${c.name}"/><br>
		学院：<input type="text" name="college" value="${c.college}"/><br>
		
		导师：<select  name="tid">
						<option value="${c.t.id }" selected="selected">${c.t.name }</option>
					<c:forEach items="${tlist}" var="t">
						<option value="${t.id }" >${t.name }</option>
					</c:forEach>								
					</select><br>
		<input type="submit" value="更新"/><br>
	</form>
</body>
</html>