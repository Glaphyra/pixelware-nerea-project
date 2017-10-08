//Controlador de la página de registro con scope y servicio $login propio
app.controller("appRegisterController", ["$scope", "$login", function($scope, $login) {
	//Customizamos la barra de navegación para acceso al login
	$scope.navbar = $login.navbar;
	
	/*
	 * Validación de la fecha de nacimiento en cliente
	 */
	//Creación del array inicial de días, de 1 a 31, para añadir al select como cadenas de texto
	$scope.days = [];
    for (var i = 1 ; i <= 31 ; i++) {
    	$scope.days.push(i.toString());
    }
    //Array de meses
	$scope.months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
	//Creación de array de años habilitados, de 1900 a 2010, añadidos como cadena de texto
    $scope.years = [];
    for (var i = 2010 ; i >= 1900 ; i--) {
    	$scope.years.push(i.toString());
    }
    
    //Datos iniciales del formulario
	$scope.day = "1";
    $scope.month = "1";
    $scope.year = "2010";
    $scope.country = "España";
    
    //Cuando cambia el mes o el año, se actualizan los días de cada mes
    $scope.updateDays = function() {
    	var max = 31;
    	switch($scope.month) {
    		//Para enero, marzo, mayo, julio, agosto, octubre y diciembre son 31 días
        	case "1":
            case "3":
            case "5":
            case "7":
            case "8":
            case "10":
            case "12":
            	max = 31;
                break;
            //Para abril, junio, septiembre y noviembre son 30 días
            case "4":
            case "6":
            case "9":
            case "11":
            	max = 30;
                break;
            case "2":
            	//Para febrero son 28 días salvo los bisiestos
            	//Un año es bisiesto si es divisible por 4, salvo los que son divisibles por 100 que no lo son, pero sí los que los son entre 400
            	//Por si no me creéis... http://www.disfrutalasmatematicas.com/medida/anos-bisiestos.html
            	if (Number($scope.year) % 4 == 0) {
                	if (Number($scope.year) % 100 == 0) {
                    	if (Number($scope.year) % 400 == 0) {
                        	max = 29;
                        } else {
                        	max = 28;
                        }
                    } else {
                    	max = 29;
                    }
                } else {
                	max = 28;
                }
                break;
        }
    	//Vaciamos el arrays de días
        $scope.days = [];
        //Y lo volvemos a llenar con los días disponibles
        for (var i = 1 ; i <= max ; i++) {
            $scope.days.push(i.toString());
        }
        //Si el día seleccionado era mayor que el disponible actual, cambia a su valor máximo disponible
        if (Number($scope.day) > max) {
        	$scope.day = max.toString();
        }
    }
}]);