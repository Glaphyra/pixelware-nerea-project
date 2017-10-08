//Controlador de la aplicación con scope y servicio $logout propio
app.controller("appController", ["$scope", "$logout", function($scope, $logout) {
	//Customizamos la barra de navegación para acceso al logout
	$scope.navbar = $logout.navbar;
	
}]);