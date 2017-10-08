//Directiva para crear la barra de navegación superior
app.directive("ngNavbar", [function() {
	var directive = {
		restrict: "E",	//Como elemento
		replace: true,	//Cambiar directiva en la vista por su contenido
		templateUrl: "resources/views/templates/navbar.html",	//Contenido html
		scope: true		//Puede acceder al scope
	};
	return directive;
}]);