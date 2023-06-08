<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<header>
		<!-- insertTemplate에선 tiles.xml에서설정한이름고 ㅏ같아야 됨ㅇㅇ -->
		<tiles:insertAttribute name="header" />
	</header>
	
	<main>
		<tiles:insertAttribute name="content" />
	</main>

</body>
</html>