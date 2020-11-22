<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 화면</title>
</head>
<body>
	<h3>${message}</h3>
	<form action='<c:url value="/Board.do"/>' method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${board.name}">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${board.email}">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="password"
					value="${board.password}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="${board.subject}">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="30" name="content">${board.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="action"
					value="${action}"> <input type="hidden" name="bbsno"
					value="${board.bbsno}"> <input type="hidden"
					name="masterid" value="${board.masterid}"> <input
					type="hidden" name="replynumber" value="${board.replynumber}">
					<input type="hidden" name="replystep" value="${board.replystep}">
					<input type="submit" value="저 장"> <input type="reset"
					value="취 소"></td>
			</tr>
		</table>
	</form>
</body>
</html>