<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지업로드</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
<!-- 	<form enctype="multipart/form-data"> -->
<!-- 비동기식 -->
<div>
		<input name="uploadFiles" type="file" multiple>
		<!-- name은 controller의 변수명이랑 같음 -->
		<!-- controller에서 배열이기 때문에 multiple임ㅇㅇ -->
		<button class="uploadBtn">Upload</button>
</div>
<!-- 	</form> -->
<script>
	$('.uploadBtn').click(function(){
		var formData = new FormData(); // FormData객체생성
		
		var inputFile = $("input[type='file']");
		// input태그의type이 file인 것을 찾아서 inputfile이라는 변수로 지정
		
		var files = inputFile[0].files;
		// files : 선택한 모든 파일을 나열하는 fileList 객체
		// muliple 특성을 지정하지 않았다면 두 개 이상의 파일을 포함하지 않음
		
		for(var i = 0; i < files.length; i++){
			console.log(files[i]);
			formData.append("uploadFiles", files[i]); // key, 값으로 append
		}

		//실제업로드부분
		//fetch
		/*
		fetch('uploadsAjax', {
			method : 'POST',
			body : formData
		})
		.then(response => response.text())
		.then(data => console.log(data))
		.catch(err => console.log(err))		
		*/
		
		//jQueryAjax
		$.ajax({
			url : 'uploadsAjax',
			type : 'POST',
			processData : false, 
			// 기본값은 true, ajax 통신을 통해 데이터를 전송 할 때, 기본적으로 key와 value값을 Query string으로 변환해서 보냄
			contentType : false, 
			// mulipart/form-data 타입을 사용하기 위해 false로 지정
			// formData사용 할 땐 상기 두 줄 필수임ㅇㅇ	
			data : formData,
			success : function(result){
				console.log(result);
				
			},
			error : function(reject){
				console.log(reject)
			}
		})
	})
</script>
</body>
</html>