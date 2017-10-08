//Directiva para crear el elemento alert con Bootstrap
app.directive("ngAlert", [function() {
	var directive = {
		restrict: "E",	//Como elemento
		replace: true,	//Cambiar directiva en la vista por su contenido
		templateUrl: "resources/views/templates/alert.html",	//Contenido html
		scope: true	//Puede acceder al scope
	};
	return directive;
}]);