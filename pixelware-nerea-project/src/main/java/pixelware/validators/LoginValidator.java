package pixelware.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pixelware.model.LoginForm;

@Component
public class LoginValidator implements Validator {
	@Override
	public boolean supports(Class<?> c) {
		return LoginForm.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Campo email y contraseña obligatorios
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
		
		//LoginForm form = (LoginForm) obj;
		//formato de contraseña
		//user.password.invalid=La contraseña debe tener entre 8 y 20 caracteres alfanuméricos
		
		//formato de email incorrecto
		//user.email.invalid=No es un email correcto
	}
}
