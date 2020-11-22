<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자입력</title>
</head>
<body>
	<form action="/Jsp.do" method="post">
		<h1>입력하신 숫자부터 10번째 뒤 숫자까지의 합을 구해줍니다.</h1>
		<br> 숫자를 입력해 주세요 : <input type="text" name="number"><br>
		<br> <input type="submit" value="제출">&nbsp;&nbsp;<input
			type="reset" value="취소">
	</form>
</body>
</html>