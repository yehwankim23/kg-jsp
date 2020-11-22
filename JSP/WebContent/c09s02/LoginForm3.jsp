<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<c:if test="${empty userid}">
		<c:out value="${msg}" />
		<br>
		<h1>로그인 폼</h1>
		<form action="/Login.do" method="post">
			아이디 : <input type="text" name="userid"><br> 비밀번호 : <input
				type="password" name="password"><br> <input
				type="submit" value="로그인"> <input type="reset" value="취 소">
		</form>
		<p>
			아이디가 없으면 <a href="/c07s03/MemberForm.jsp">회원가입</a>하세요.
	</c:if>
	<c:if test="${!empty userid}">
		<h1>마이페이지</h1>
			${userid} 님 환영합니다.<br>
		<a href="/Login.do?action=logout">로그아웃</a>
		<p>
			<a href="/c08s01/auth/MemberOnly.html">회원전용 페이지</a> <a
				href="/Member.do?action=select">회원 정보 수정</a>
	</c:if>
</body>
</html>