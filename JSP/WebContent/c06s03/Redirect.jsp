<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Redirect</title>
</head>
<body>
	<%
		String name = (String) request.getAttribute("name");
	%>
	<h1>기본 출력</h1>
	<%
		if (name != null) {
	%>
	<%=name%>
	<%
		}
	%>
</body>
</html>