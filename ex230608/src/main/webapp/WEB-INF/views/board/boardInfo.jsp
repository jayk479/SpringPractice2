<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<td>${board.bno}</td>
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="2" style="width: 100px;" readonly>${board.contents}</textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<c:choose>
				<c:when test="${not empty board.image}">
					<!-- 방법1 <td><img src="pageContext.request.contextPath/resources/images/board.image"></td> -->
					<td><img
						src='<c:url value="/resources/images/${board.image}"/>' style="width:400px;"></td>
				</c:when>
				<c:otherwise>
					<td>파일없음</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${board.regdate}" pattern="yy-MM-dd" /></td>
		</tr>
		<tr>
			<th>수정일자</th>
			<td><fmt:formatDate value="${board.updatedate}"	pattern="yy-MM-dd" /></td>
		</tr>
	</table>
	<button type="button"
		onclick="location.href='boardUpdate?bno=${board.bno}'">수정</button>
	<button type="button"
		onclick="location.href='boardDelete?bno=${board.bno}'">삭제</button>
</body>
</html>
