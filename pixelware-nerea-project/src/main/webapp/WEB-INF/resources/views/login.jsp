<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%-- Declaramos el módulo de la aplicación --%>
<html ng-app="pixelwareApp">
	<head>
		<title>Login</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	</head>
	
	<%-- Declaramos el controlador de la aplicación --%>
	<body ng-controller="appLoginController">
		<%-- Insertamos la barra de navegación --%>
		<ng-navbar></ng-navbar>
		<%-- Si hay un mensaje de alerta, cargamos la capa alerta con su directiva --%>
		<c:if test="${!empty alert}">
			<ng-alert ng-init="message = '${alert}'"></ng-alert>
		</c:if>
		
		<div class="container">
			<%-- Formulario de inicio de sesión --%>
			<form:form class="form-horizontal" method="post" modelAttribute="loginUser" name="loginForm">
				<div class="form-group"ng-class="{'has-success has-feedback': (loginForm.email.$valid && loginForm.email.$dirty), 'has-error has-feedback': (loginForm.email.$invalid && loginForm.email.$dirty)}">
					<label class="control-label col-sm-3" for="email">Email:</label>
					<div class="col-sm-9">
						<form:input path="email" class="form-control" id="email" placeholder="Email" ng-model="email" ng-maxlength="40" required="required" ng-validate-email="true" />
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
                    	<form:errors path="email" />
                     </div>
				</div>
				<div class="form-group" ng-class="{'has-success has-feedback': (loginForm.password.$valid && loginForm.password.$dirty), 'has-error has-feedback': (loginForm.password.$invalid && loginForm.password.$dirty)}">
					<label class="control-label col-sm-3" for="password">Contraseña:</label>
					<div class="col-sm-9"> 
						<form:password path="password" class="form-control" id="password" placeholder="Contraseña" ng-model="password" ng-minlength="8" ng-maxlength="20" required="required" />
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="password" />
                    </div>
				</div>
				
				<div class="form-group center"> 
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-default w3-cyan w3-text-white btn-block" ng-class="{'active' : loginForm.$valid, 'disabled' : loginForm.$invalid}">Iniciar sesión</button>
					</div>
				</div>
				 
			</form:form>
		</div>
		<%-- Enlace a registro --%>
		<div class="container w3-right-align">
			<u><a href="register">¿Aún no estás registrado?</a></u>
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
		<script type="text/javascript" src="resources/scripts/directives/emailValidator.js"></script>
		<script type="text/javascript" src="resources/scripts/directives/navbar.js"></script>
		<script type="text/javascript" src="resources/scripts/directives/alert.js"></script>
		<!-- Controlador de la aplicación -->
		<script type="text/javascript" src="resources/scripts/controller/login.js"></script>
	</body>
</html>