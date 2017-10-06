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
		${weather.getLocation().getName()} (${weather.getLocation().getCountry()}): ${weather.getCurrent().getTemp_c()}ºC
	</body>
</html>