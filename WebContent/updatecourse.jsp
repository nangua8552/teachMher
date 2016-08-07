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
	<form action="CourseServlet"  method="post">
		<input type="hidden" name="op"value="update"/>
		<input type="hidden" name="id"value="${c.id}"/>
		课名：<input type="text" name="name" value="${c.name}"/><br>
		学分：<input type="text" name="credit" value="${c.credit}"/><br>
		
		<input type="submit" value="更新"/><br>
	</form>
</body>
</html>