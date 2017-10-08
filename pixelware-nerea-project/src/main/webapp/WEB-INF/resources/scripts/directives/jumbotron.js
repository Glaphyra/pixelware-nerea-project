//Directiva para crear el elemento Jumbotron con Bootstrap
app.directive("ngJumbotron", [function() {
	var directive = {
		restrict: "E",	//Como elemento
		replace: true,	//Cambiar directiva en la vista por su contenido
		templateUrl: "resources/views/templates/jumbotron.html",	//Contenido html
		scope: true		//Puede acceder al scope
	};
	return directive;
}]);