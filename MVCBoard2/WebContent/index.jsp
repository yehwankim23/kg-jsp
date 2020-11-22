<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/default.css" media="screen">
<title>첫 화면</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<!--  여기서부터 본문 -->
				<jsp:include page="${empty param.url ? '/home.jsp' : param.url}" /><br>
			</td>
		</tr>
	</table>
</body>
</html>