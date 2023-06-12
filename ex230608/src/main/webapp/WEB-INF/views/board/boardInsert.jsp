<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
	<div>
		<h3>게시글 정보</h3>
	</div>
	<!-- <form name="insertForm" action="boardInsert" method="POST" onsubmit="beforeSubmit(event);"> -->
		<form name="insertForm" action="boardInsert" method="POST" >
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="contents"></textarea></td>
			</tr>
			<tr>
				<th>첨부이미지</th>
				<td><input type="text" name="image"></td>
			</tr>
		</table>
		<button type="submit">등록</button>
		<button type="button" onclick="location.href='boardList'">목록</button>
	</form>

	<script>

		document.querySelect('[name="insertForm"]').addEventListener('submit', function(e){
			e.preventDefault(); //해당 이벤트와 관련되어 기존 등록된 이벤트 막음
			let title = document.getElementsByName('title')[0];
			let writer = document.getElementsByName('writer')[0];
			let contents = document.getElementsByName('contents')[0];

			if (title.value == "") {
				alert("제목적어주세요");
				title.focus();
				return;
			} 
			if (writer.value == "") {
				alert("작성자적어주세요");
				writer.focus();
				return;
			} 
			if (contents.value == "") {
				alert("내용적어주세요");
				//document.getElementsByName('contents').focus();
				return;
			} 
			insertForm.submit();
			
		})
		
		// function beforeSubmit(event) {
		// 	event.preventDefault();

		// 	let titleValue = document.getElementsByName('title')[0].value;
		// 	let writerValue = document.getElementsByName('writer')[0].value;
		// 	let contentsValue = document.getElementsByName('contents')[0].value;
		// 	if (titleValue == "") {
		// 		alert("제목적어주세요");
		// 		document.getElementsByName('title')[0].focus();
		// 	} else if (writerValue == "") {
		// 		alert("작성자적어주세요");
		// 		document.getElementsByName('writer')[0].focus();
		// 	} else if (contentsValue == "") {
		// 		alert("내용적어주세요");
		// 		//document.getElementsByName('contents')[0].focus();
		// 	} else {
		// 		insertForm.submit();
		// 	}
		// 	//let delBtnList = Array.from(document.getElementsByTagName('button')).filter(item => item.id != "checkDel")
		// }
	</script>
</body>
</html>