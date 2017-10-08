<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%-- Declaramos el módulo de la aplicación --%>
<html ng-app="pixelwareApp">
	<head>
		<title>Pixelware App</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="resources/styles/css.css">
	</head>
	
	<%-- Declaramos el controlador de la aplicación e inicializamos la variable history (del scope) con los datos obtenidos por Java --%>
	<body ng-controller="appController" data-ng-init="setList('${history}')">
		<%-- Insertamos la barra de navegación --%>
		<ng-navbar></ng-navbar>
		<%-- Si hay un mensaje de alerta, cargamos la capa alerta con su directiva --%>
		<c:if test="${!empty alert}">
			<ng-alert ng-init="message = '${alert}'"></ng-alert>
		</c:if>
		<%-- Insertamos la cabecera de la página --%>
		<ng-jumbotron></ng-jumbotron>
		
		<%-- Tabla para ver todas las búsquedas --%>
		<div class="container topMargin">
			<table class="table">
            	<thead>
                	<tr class="w3-cyan">
                    	<th>Ciudad</th>
                    	<th>Pais</th>
                    	<th>Temperatura</th>
                    </tr>
                </thead>
            	<tbody>
                	<tr ng-repeat="h in history" class="w3-hover-cyan">
                    	<td>{{h.location.name}}</td>
                    	<td>{{h.location.country}}</td>
                    	<td>{{h.current.temp_c}}ºC</td>
                    </tr>
                    <tr>
                    	<td colspan="3" class="center w3-cyan">
                    		<a href="inicio">Volver al inicio</a>
                    	</td>
                    </tr>
                </tbody>
            </table>
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