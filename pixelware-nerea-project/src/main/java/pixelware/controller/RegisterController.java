package pixelware.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.RegisterForm;
import pixelware.model.User;
import pixelware.service.IUserService;
import pixelware.validators.RegisterValidator;

/*
 * Controlador para registrar un nuevo usuario
 */
@Controller
public class RegisterController {
	@Autowired
	private IUserService service;
	@Autowired
	private RegisterValidator validator;
	
	//Añadimos el validador
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	/*
	 * Método de negocio para mostrar formulario de registro
	 */
	@GetMapping("/register")
	public ModelAndView getLogin() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("registerUser", new RegisterForm());
		return model;
	}
	
	/*
	 * Método de negocio para recoger los datos del formulario
	 * Validar campos y comprobar que el usuario es correcto
	 */
	@PostMapping("/register")
	public ModelAndView getLogin(@ModelAttribute("registerUser") @Validated RegisterForm registerUser, BindingResult result, HttpServletResponse response, HttpSession session) {
		ModelAndView model = new ModelAndView();
		//Si los campos no son correctos según la validación, lo devolvemos a la página de registro y mostramos los errores
		if (result.hasErrors()) {
			model.setViewName("register");
		} else {
			//Creamos una instancia usuario a partir de los datos del formulario
			User user = new User(registerUser);
			//Enviamos a la base de datos el usuario creado
			int i;
			try {
				i = service.addUser(user);
				//Si devuelve -1 es porque ha habido un error y lo notificamos
				if (i <= -1) {
					model.addObject("alert", "No se ha podido registrar. Compruebe los campos y vuelva a intentarlo.");
					model.setViewName("register");
					model.addObject("registerUser", new RegisterForm());
				} else {
					//Si el registro ha sido exitoso, añadimos el usuario creado a la sesión
					session.setAttribute("user", user);
					//Y redireccionamos a la página de inicio
					try {
						response.sendRedirect("/");
						return null;
					} catch (IOException e) {
						model.setViewName("error");
						model.addObject("alert", "Ha habido un error desconocido.");
					}
				}
			} catch (ParseException e) {
				//Si se produce un error al pasar de Localdate a Date
				model.addObject("alert", "Hubo un error con la fecha de nacimiento. Vuelva a intentarlo");
				model.setViewName("register");
				model.addObject("registerUser", new RegisterForm());
			}
		}
		return model;
	}
}