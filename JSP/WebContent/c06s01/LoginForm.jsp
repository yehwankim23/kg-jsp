<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>로그인 폼</title>
</head>
<body>
	<%
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
	%>
	<h1>로그인 폼</h1>
	<form action="/Login" method="post">
		아이디 : <input type="text" name="userid"><br> 비밀번호 : <input
			type="password" name="password"><br> <input
			type="hidden" name="action" value="login"> <input
			type="submit" value="로그인"> <input type="reset" value="취 소">
	</form>
	아이디가 없으면
	<a href="/Member/MemberForm.jsp">회원가입</a>하세요.
	<%
		} else {
			out.println("<h1>마이페이지</h1>");
			out.println(userid + "님 로그인 중");
			out.println("로그아웃하시겠습니까?");
	%>
	<br>
	<form action="/Login" method="post">
		<input type="submit" value="네"> <input type="hidden"
			value="logout" name="action">
	</form>
	<%
		}
	%>
</body>
</html>