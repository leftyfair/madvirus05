<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
<h2>회원목록</h2>
<form:form modelAttribute="listCommand" action="members">
	<div>
		<label> 시작 : 
			<form:input path="from"/>	
			<form:errors path="from"/>	

		</label>
		~
		<label> 끝 :
			<form:input path="to"/>			
			<form:errors path="to"/>			
		</label>
		<input type="submit" value="조회">
		<a href="${contextPath}/memberList">전체목록</a>
		
	</div>
</form:form>
<c:if test="${not empty members }">
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
		</tr>
		<c:forEach items="${members }" var="m">
		<tr>
			<td>${m.id }</td>
			<td>${m.name }</td>
			<td>
				<a href="${contextPath }/members/${m.id}">${m.email }</a>
			</td>
			<td>
				<fmt:parseDate value="${m.registerDateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime" />
				<fmt:formatDate value="${parseDateTime}" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>