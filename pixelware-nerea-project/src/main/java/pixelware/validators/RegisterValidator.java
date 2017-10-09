package pixelware.validators;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pixelware.model.RegisterForm;

@Component
public class RegisterValidator implements Validator {
	@Override
	public boolean supports(Class<?> c) {
		return RegisterForm.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Campo email y contraseña obligatorios
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "day", "user.day.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "month", "user.month.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "user.year.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "user.country.empty");
		
		RegisterForm form = (RegisterForm) obj;
		
		/*
		 * Comprobar el formato de la contraseña
		 * Debe tener entre 8 y 20 caracteres ( {8,20} )
		 * Solo puede contener letras (mayúsculas o minúsculas), números o los caracteres . - _ +
		 */
		Pattern patternPassword = Pattern.compile("^[a-zA-Z0-9_\\-\\.\\+]{8,20}$");
		if (!(patternPassword.matcher(form.getPassword()).matches())) {
			errors.rejectValue("password", "user.password.invalid");
		}
		
		/*
		 * Comprobar si el formato del email es correcto.
		 * El email debe comenzar por una letra o  número ( ^[_A-Za-z0-9]+ )
		 * Opcionalmente puede llevar otras letras, números, _ - tras un punto ( (\\.[_A-Za-z0-9-]+)* )
		 * Debe llevar una arroba ( @ )
		 * El nombre del dominio debe comenzar por una letra, número o - al menos una vez ( [A-Za-z0-9-]+ )
		 * Puede llevar un primer dominio empezando por punto seguido de al menos una letra o número ( (\\.[A-Za-z0-9]+)* )
		 * Debe finalizar con un punto seguido de dos letras, máximo 5 ( (\\.[A-Za-z]{2,5})$ )
		 * 
		 * También la longitud máxima es de 40 caracteres (creado en base de datos como VARCHAR(40))
		 */
		Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,5})$");
		if (!(patternEmail.matcher(form.getEmail()).matches()) || form.getEmail().length() > 40) {
			errors.rejectValue("email", "user.email.invalid");
		}

		testDate(errors, Integer.parseInt(form.getDay()), Integer.parseInt(form.getMonth()), Integer.parseInt(form.getYear()) );
	}
	
	private void testDate(Errors errors, int day, int month, int year) {
		if (month < 1 && month > 12)
			//Si el mes es menor que 1 o mayor que 12, la fecha no es válida
			errors.rejectValue("month", "user.date.invalid");
		if (year < 1900 && year > 2010)
			//Si el año es inferior a 1900 o superior a 2010 la fecha no será válida
			errors.rejectValue("year", "user.date.invalid");
		if (day < 1 && day > 31) {
			//Si el día es menor que 1 o mayor que 31 no es válido
			errors.rejectValue("day", "user.date.invalid");
		} else {
			int max = 31;
			switch(month) {
	    		//Para enero, marzo, mayo, julio, agosto, octubre y diciembre son 31 días
	        	case 1: case 3: case 5: case 7: case 8: case 10: case 12:
	            	max = 31;
	                break;
	            //Para abril, junio, septiembre y noviembre son 30 días
	            case 4: case 6: case 9: case 11:
	            	max = 30;
	                break;
	            case 2:
	            	//Para febrero son 28 días salvo los bisiestos
	            	//Un año es bisiesto si es divisible por 4, salvo los que son divisibles por 100 que no lo son, pero sí los que los son entre 400
	            	if (year % 4 == 0) {
	                	if (year % 100 == 0) {
	                    	if (year % 400 == 0) {
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
	            //Cualquier otro mes introducido por error será inválido
	            default:
	            	max = Integer.MIN_VALUE;
			}
			//Si el día es mayor que el máximo en el mes elegido
			if (day > max)
				errors.rejectValue("day", "user.date.invalid");
		}
	}
}
