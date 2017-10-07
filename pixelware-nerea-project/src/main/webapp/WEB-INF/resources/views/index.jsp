<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Pixelware App</title>
	</head>
	<body>
		<a href="logout">Cerrar sesión</a>
		<br />
		<c:if test="${!empty alert}">
			<span style="{color:red}">${alert}</span>
		</c:if>
		<form:form method="POST" action="getDegrees" modelAttribute="city">
			<form:input path="name" class="form-control" placeholder="Ciudad" />
			<button type="submit">Enviar</button>
		</form:form>
	</body>
</html>