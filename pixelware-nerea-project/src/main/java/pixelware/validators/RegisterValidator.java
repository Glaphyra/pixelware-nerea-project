package pixelware.validators;

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
		
		//LoginForm form = (LoginForm) obj;
		//formato de contraseña
		//user.password.invalid=La contraseña debe tener entre 8 y 20 caracteres alfanuméricos
		
		//formato de email incorrecto
		//user.email.invalid=No es un email correcto
		
		//#fecha no válida, el mensaje se ve solo el en año 	user.day.invalid=  user.month.invalid=   user.year.invalid=Esa fecha no existe
	}
}
