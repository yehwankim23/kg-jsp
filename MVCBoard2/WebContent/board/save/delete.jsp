<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제 화면</title>
</head>
<body>
	<h3>글 삭제 비밀번호 입력</h3>
	<form action='<c:url value="/Board.do"/>' method="post">
		<input type="hidden" name="action" value="${action}"> <input
			type="hidden" name="bbsno" value="${bbsno}"> <input
			type="hidden" name="replynumber" value="${replynumber}"> <input
			type="password" name="password"> <input type="submit"
			value="삭 제">
	</form>
</body>
</html>