<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<style>
div>p {
	display: inline-block;
	margin-left: 10px;
}
</style>
</head>
<body>
	<form action="/insertName.do" method="post">
		<input type="text" name="name"> <input type="text" name="tel">
		<input type="submit" value="전송">
	</form>
	<div>
		<c:forEach items="${nameList }" var="name" varStatus="status">
			<div style="border: 1px solid black">
				<c:if test="${name.name == '홍길동' }">
					<p style="color: blue;">${status.count }</p>
					<p style="color: blue;">${status.index }</p>
					<p style="color: blue;">${name.name }</p>
					<p style="color: blue;">${name.tel }</p>
				</c:if>
				<c:if test="${name.name != '홍길동' }">
					<p>${status.count }</p>
					<p>${status.index }</p>
					<p>${name.name }</p>
					<p>${name.tel }</p>
				</c:if>
			</div>
		</c:forEach>
		<c:forEach items="${nameList }" var="name" varStatus="status">
			<div style="border: 1px dotted red">
				<c:choose>
					<c:when test="${name.name == '고라니' }">
						<p style="color: blue;">${status.count }</p>
						<p style="color: blue;">${status.index }</p>
						<p style="color: blue;">${name.name }</p>
						<p style="color: blue;">${name.tel }</p>
					</c:when>
					<c:otherwise>
						<p>${status.count }</p>
						<p>${status.index }</p>
						<p>${name.name }</p>
						<p>${name.tel }</p>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</div>
</body>
</html>
