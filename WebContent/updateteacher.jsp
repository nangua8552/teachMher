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
	<form action="TeacherServlet"  method="post">
		<input type="hidden" name="op"value="update"/>
		<input type="hidden" name="id"value="${t.id}"/>
		姓名：<input type="text" name="name" value="${t.name}"/><br>
		年龄：<input type="text" name="age" value="${t.age}"/><br>
		<c:if test="${t.sex=='1' }">
				<input type="radio" name="sex" value="1"  checked="checked"/>男<input type="radio" name="sex" value="0"/>女<br>
			</c:if>
			<c:if test="${t.sex=='0' }">
				<input type="radio" name="sex" value="1" />男<input type="radio" name="sex" value="0"  checked="checked"/>女<br>
			</c:if>
		职称：<input type="text" name="lev" value="${t.lev}"/><br>
		<input type="submit" value="更新"/><br>
	</form>
</body>
</html>