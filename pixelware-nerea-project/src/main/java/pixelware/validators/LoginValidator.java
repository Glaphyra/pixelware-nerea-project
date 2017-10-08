package pixelware.validators;

import java.util.regex.Pattern;

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
		
		LoginForm form = (LoginForm) obj;
		/*
		 * Comprobar el formato de la contraseña (igual que en RegisterLogin)
		 */
		Pattern patternPassword = Pattern.compile("^[a-zA-Z0-9_\\-\\.\\+]{8,20}$");
		if (!(patternPassword.matcher(form.getPassword()).matches())) {
			errors.rejectValue("password", "user.password.invalid");
		}
		
		/*
		 * Comprobar si el formato del email es correcto (igual que en RegisterLogin)
		 */
		Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,5})$");
		if (!(patternEmail.matcher(form.getEmail()).matches()) || form.getEmail().length() > 40) {
			errors.rejectValue("email", "user.email.invalid");
		}
	}
}
