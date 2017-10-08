<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="resources/styles/css.css">
		<title>Error</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand w3-text-cyan" href="#"> Proyecto Pixelware</a>
				</div>
			</div>
		</nav>
		
		<div class="jumbotron w3-red">
			<div class="container text-center">
				<h1><span class="glyphicon glyphicon-warning-sign"></span> ¡Error! <span class="glyphicon glyphicon-warning-sign"></span></h1>
				<p>${alert}</p>
				<p><a href="login">Volver a inicio</a></p>
			</div>
		</div>
	</body>
</html>