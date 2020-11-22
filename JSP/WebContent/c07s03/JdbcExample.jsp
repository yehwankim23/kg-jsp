<%@ page import="c07s03.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jdbc</title>
</head>
<body>
	<H1>사원 정보</H1>
	<%
		Employee emp = (Employee) request.getAttribute("emp");
	%>
	<%=emp.toString()%>
	<form action="/Jdbc2.do">
		정보를 보고 싶은 사원의 사원번호를 입력하세요.<br> <input type="text" name="empno">
		<input type="submit" value="전 송"> <input type="reset"
			value="취 소">
	</form>
</body>
</html>