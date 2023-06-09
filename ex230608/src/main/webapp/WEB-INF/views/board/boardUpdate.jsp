<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<form name="updateForm" action="boardUpdate" method="post" onsubmit="return false;"> <!-- form태그의 submit 버튼을 동작 xxx -->
		<div>
			<h3>게시글 수정</h3>
		</div>
		<table>
			<tr>
				<th>번호</th>
				<td><input type="NUMBER" name="bno" value="${board.bno}"
					readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${board.title}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents">${board.contents}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="text" name="image" value="${board.image}"></td>
			</tr>
			<tr>
				<th>수정날짜</th>
				<td><input type="date" name="updatedate" value='<fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd"/>'/></td>
			</tr>
		</table>
		<button type="submit">수정완료</button>
		<button type="button" onclick="location.href='boardInfo?bno=${board.bno}'">취소</button>
	</form>
	<script>
		//form은 내부 데이터 개수 상관없이 한꺼번에 가져 올 수 있음ㅇㅇ
		//수정완료버튼을 동작시키기 위한 비동기
		function updateAjax(e){
			let boardData = new FormData(document.querySelector("[name='updateForm']"));
			// 객체이지만 json아님
			fetch(updateForm.action, {
				method : 'POST',
				body : boardData
			})
			.then(response => response.json()) //parse해줘야됨ㅇㅇ cuz json아니기떄문에ㅇㅇ
			.then(data => {
				console.log(data);
				let message = '결과 : '+ data.result +
							   ', 게시글번호 : ' + data['board_no'];
				alert(message)
			})
			.catch(err => console.log(err));
		}
		document.querySelector('button[type="submit"]').addEventListener('click', updateAjax);
	</script>
</body>
</html>