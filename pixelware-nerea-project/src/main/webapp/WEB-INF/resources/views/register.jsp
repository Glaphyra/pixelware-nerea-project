<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>New User</title>
	</head>
	<body>
		<form:form method="post" modelAttribute="registerUser">
			Email <form:input path="email"/> <form:errors path="email" style="color:red"/>
			<br />
			Contraseña <form:password path="password"/> <form:errors path="password" style="color:red"/>
			<br />
			País de residencia <form:select path="country">
				<option value="es">España</option>
				<option value="uk">Reino unido</option>
				<option value="fr">Francia</option>
				<option value="de">Alemania</option>
				<option value="it">Italia</option>
			</form:select>
			<br />
			Fecha de nacimiento
			<br />
			Día <form:input path="day"/> <form:errors path="day" style="color:red"/>
			Mes <form:input path="month"/> <form:errors path="month" style="color:red"/>
			Año <form:input path="year"/> <form:errors path="year" style="color:red"/>
			<br />
			<input type="submit" value="submit" />
			<c:if test="${!empty message}">
				<br/><span style="color: red">${message}</span>
			</c:if>
		</form:form>
	</body>
</html>