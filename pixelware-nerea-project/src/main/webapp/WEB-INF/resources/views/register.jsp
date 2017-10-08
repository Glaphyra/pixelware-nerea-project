<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%-- Declaramos el módulo de la aplicación --%>
<html ng-app="pixelwareApp">
	<head>
		<title>New User</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	</head>
	
	<%-- Declaramos el controlador de la aplicación --%>
	<body ng-controller="appRegisterController">
		<%-- Insertamos la barra de navegación --%>
		<ng-navbar></ng-navbar>
		<%-- Si hay un mensaje de alerta, cargamos la capa alerta con su directiva --%>
		<c:if test="${!empty alert}">
			<ng-alert ng-init="message = '${alert}'"></ng-alert>
		</c:if>
		
		<div class="container">
			<%-- Formulario de registro --%>
			<form:form class="form-horizontal" method="post" modelAttribute="registerUser" name="registerForm">
				<div class="form-group">
					<label class="control-label col-sm-3" for="email">Email:</label>
					<div class="col-sm-9" ng-class="{'has-success has-feedback': (registerForm.email.$valid && registerForm.email.$dirty), 'has-error has-feedback': (registerForm.email.$invalid && registerForm.email.$dirty)}">
						<form:input path="email" class="form-control" id="email" placeholder="Email" ng-validate-email="true" ng-model="email" ng-required="true" ng-maxlength="40" />
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
                    	<form:errors path="email" />
                     </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="password">Contraseña:</label>
					<div class="col-sm-9" ng-class="{'has-success has-feedback': (registerForm.password.$valid && registerForm.password.$dirty), 'has-error has-feedback': (registerForm.password.$invalid && registerForm.password.$dirty)}"> 
						<form:password path="password" class="form-control" id="password" placeholder="Contraseña" ng-model="password" ng-minlength="8" ng-maxlength="20" ng-required="true" />
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="password" />
                    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="country">Ciudad de residencia:</label>
					<div class="col-sm-9" ng-class="{'has-success has-feedback': (registerForm.country.$valid && registerForm.country.$touched), 'has-error has-feedback': (registerForm.country.$invalid && registerForm.country.$touched)}">
						<form:select path="country" class="form-control" id="country" ng-model="country" ng-required="true">
							<option value="Alemania">Alemania</option>
							<option value="Austria">Austria</option>
							<option value="Bélgica">Bélgica</option>
							<option value="Dinamarca">Dinamarca</option>
							<option value="España">España</option>
							<option value="Finlandia">Finlandia</option>
							<option value="Francia">Francia</option>
							<option value="Grecia">Grecia</option>
							<option value="Irlanda">Irlanda</option>
							<option value="Italia">Italia</option>
							<option value="Portugal">Portugal</option>
							<option value="Reino Unido">Reino Unido</option>
							<option value="Suecia">Suecia</option>
							<option value="Otro">Otro</option>
						</form:select>
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="country" />
                    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="date">Fecha de nacimiento:</label>
					<div class="col-sm-3" ng-class="{'has-success has-feedback': (registerForm.day.$valid && registerForm.day.$touched), 'has-error has-feedback': (registerForm.day.$invalid && registerForm.day.$touched)}">
						<form:select path="day" class="form-control" id="day" ng-model="day" ng-required="true">
							<option ng-repeat="d in days" value="{{d}}">{{d}}</option>
						</form:select>
					</div>
					<div class="col-sm-3" ng-class="{'has-success has-feedback': (registerForm.month.$valid && registerForm.month.$touched), 'has-error has-feedback': (registerForm.month.$invalid && registerForm.month.$touched)}">
						<form:select path="month" class="form-control" id="month" ng-model="month" ng-required="true" ng-change="updateDays()">
							<option ng-repeat="m in months" value="{{$index+1}}">{{m}}</option>
						</form:select>
					</div>
					
					<div class="col-sm-3" ng-class="{'has-success has-feedback': (registerForm.year.$valid && registerForm.year.$touched), 'has-error has-feedback': (registerForm.year.$invalid && registerForm.year.$touched)}">
						<form:select path="year" class="form-control" id="year" ng-model="year" ng-required="true" ng-change="updateDays()">
							 <option ng-repeat="y in years" value="{{y}}">{{y}}</option>
						</form:select>
					</div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="day" />
                    </div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="month" />
                    </div>
					<div class="col-sm-offset-3 col-sm-9 w3-text-red w3-right-align">
						<form:errors path="year" />
                    </div>
				</div>
				<div class="form-group center"> 
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-default w3-cyan w3-text-white btn-block" ng-class="{'active' : registerForm.$valid, 'disabled' : registerForm.$invalid}">Registrarse</button>
					</div>
				</div>
			</form:form>
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
		<script type="text/javascript" src="resources/scripts/controller/register.js"></script>
	</body>
</html>