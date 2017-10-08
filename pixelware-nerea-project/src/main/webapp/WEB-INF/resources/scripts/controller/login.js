//Controlador de la aplicación con scope y servicio $login propio
app.controller("appLoginController", ["$scope", "$login", function($scope, $login) {
	//Customizamos la barra de navegación para acceso al login
	$scope.navbar = $login.navbar;
}]);