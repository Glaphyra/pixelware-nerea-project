<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
	</head>
	<body>
		<form:form method="post" modelAttribute="loginUser">
			Email <form:input path="email"/> <form:errors path="email" style="color:red"/>
			<br />
			Contraseña <form:password path="password"/> <form:errors path="password" style="color:red"/>
			<br />
			<input type="submit" value="submit" />
			<c:if test="${!empty message}">
				<br/><span style="color: red">${message}</span>
			</c:if>
		</form:form>
		<a href="register">¿Aún no estás registrado?</a>
	</body>
</html>