//Controlador de la aplicación con scope y servicio $logout propio
app.controller("appController", ["$scope", "$logout", function($scope, $logout) {
	//Customizamos la barra de navegación para acceso al logout
	$scope.navbar = $logout.navbar;
	
	//Recogemos el objeto json de Java y loo guardamos en Angular
	$scope.setList = function(json) {
		var array = JSON.parse(json);
		//Antes de guardarlo lo invertimos, para mostrar primero el registro más nuevo
		$scope.history = array.reverse();
	}
}]);