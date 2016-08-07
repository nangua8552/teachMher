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
	<form action="StudentServlet"  method="post">
			<input type="hidden" name="op" value="update"/>
			学号：<input type="text" name="sno" value="${s.sno}" readonly="readonly"/><br>
			姓名：<input type="text" name="name" value="${s.name }"/><br>
			年龄：<input type="text" name="age" value="${s.age }"/><br>
			性别：<c:if test="${s.sex=='1' }">
					<input type="radio" name="sex" value="1"  checked="checked"/>男<input type="radio" name="sex" value="0"/>女<br>
				</c:if>
				<c:if test="${s.sex=='0' }">
					<input type="radio" name="sex" value="1" />男<input type="radio" name="sex" value="0"  checked="checked"/>女<br>
				</c:if>
			电话：<input type="text" name="tel" value="${s.tel }"/><br>
			地址：<input type="text" name="address" value="${s.address }"/><br>
			班级：<select  name="cid">
						<option value="${s.c.id }" selected="selected">${s.c.name }</option>	
					<c:forEach items="${clist }" var="c">
						<option value="${c.id }" >${c.name }</option>
					</c:forEach>								
					</select>
					<br>
			<input type="submit" value="更新"/><br>
</form>
</body>
</html>