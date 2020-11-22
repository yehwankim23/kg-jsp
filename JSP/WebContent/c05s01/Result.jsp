<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 출력</title>
</head>
<body>
	<%
		String number = (String) request.getAttribute("number");
	%>
	입력하신 숫자에서 10번째 자리뒤까지의 합은 :
	<%=number%>
</body>
</html>