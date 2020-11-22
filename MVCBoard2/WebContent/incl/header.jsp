<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<a href='<c:url value="/"/>'>홈으로</a> <a
		href='<c:url value="/Board.do?action=write"/>'>게시글 쓰기</a> <a
		href='<c:url value="/Board.do?action=list"/>'>게시글 목록</a>
</header>