<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원정보입력</h2>
<form:form action="step3" modelAttribute="registerCommand">
	<div>
		<label> 이메일 :
			<form:input path="email"/>
			<form:errors path="email"/>
		</label>
	</div>
	<div>
		<label> 이름 :
			<form:input path="name"/>
			<form:errors path="name"/>
		</label>
	</div>
	<div>
		<label> 비밀번호 :
			<form:password path="password"/>
			<form:errors path="password"/>
		</label>
	</div>
	<div>
		<label> 비밀번호 확인 :
			<form:password path="confirmPassword"/>
			<form:errors path="confirmPassword"/>
		</label>
	</div>
	<input type="submit" value="회원가입">
</form:form>
</body>
</html>