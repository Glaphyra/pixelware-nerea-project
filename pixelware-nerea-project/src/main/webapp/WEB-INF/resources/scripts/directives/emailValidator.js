//Creación de directiva para validar un email
app.directive('ngValidateEmail', function() {
	return {
		require: 'ngModel', //Es necesaria la directiva ngModel
		link: function(scope, element, attribute, controller) { //Función enlace. Recibimos en el 4º argumento el controlador
			function validator(value) { //Función para validar el valor del input
				//Misma expresión regular que en RegisterValidator
				var pattern = new RegExp("^[_A-Za-z0-9]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,5})$");
				
				//Comprobar si el email coincide con el formato es válido
				if (pattern.test(value)) {
					controller.$setValidity('isEmail', true);
				} else {
					controller.$setValidity('isEmail', false);
				}
				return value;
			}
			//Añadimos la función de validación creada al array de funciones del controlador
			controller.$parsers.push(validator);
		}
	};
});