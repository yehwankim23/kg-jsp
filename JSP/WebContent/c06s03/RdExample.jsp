<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 데이터 출력</title>
</head>
<body>
	<%
		String id = (String) request.getAttribute("id");
		String name = (String) request.getAttribute("name");
		String pw = (String) request.getAttribute("pw");
	%>
	<h1>입력하신 정보가 아래와 같습니까?</h1>
	<h3><%=id%></h3>
	<h3><%=pw%></h3>
	<h3><%=name%></h3>
</body>
</html>