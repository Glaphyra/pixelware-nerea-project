<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="pixelwareApp">
	<head>
		<title>Pixelware App</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="resources/styles/css.css">
	</head>
	
	<body ng-controller="appController">
		<ng-navbar></ng-navbar>
		<c:if test="${!empty alert}">
			<ng-alert ng-init="message = '${alert}'"></ng-alert>
		</c:if>
		<ng-jumbotron></ng-jumbotron>
		
		<div class="container">
			<form:form action="getDegrees" method="post" modelAttribute="city" name="cityForm">
				<div class="input-group" ng-class="{'has-success has-feedback': (cityForm.name.$valid && cityForm.name.$dirty), 'has-error has-feedback': (cityForm.name.$invalid && cityForm.name.$dirty)}">
					<form:input path="name" class="form-control" placeholder="Buscar ciudad" ng-model="name" required="required" />
					<div class="input-group-btn">
						<button class="btn btn-default w3-cyan w3-text-white" type="submit" ng-class="{'active' : cityForm.$valid, 'disabled' : cityForm.$invalid}">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form:form>
			
			<br/>
			<div class="panel panel-default">
				<div class="panel-heading w3-cyan">${weather.getLocation().getName()} (${weather.getLocation().getCountry()})</div>
				<div class="panel-body">${weather.getCurrent().getTemp_c()}ºC</div>
			</div>
		</div>
		
		<!-- Carga de recursos JavaScript -->
		<!-- AngularJS -->
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
		<!-- jQuery y Bootstrap -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- Módulo de la aplicación -->
		<script type="text/javascript" src="resources/scripts/module.js"></script>
		<!-- Servicios (constantes de login y logout) -->
		<script type="text/javascript" src="resources/scripts/services/navbarServices.js"></script>
		<!-- Directivas -->
		<script type="text/javascript" src="resources/scripts/directives/navbar.js"></script>
		<script type="text/javascript" src="resources/scripts/directives/jumbotron.js"></script>
		<script type="text/javascript" src="resources/scripts/directives/alert.js"></script>
		<!-- Controlador de la aplicación -->
		<script type="text/javascript" src="resources/scripts/controller/main.js"></script>
	</body>
</html>