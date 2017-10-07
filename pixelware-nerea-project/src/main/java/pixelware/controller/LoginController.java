package pixelware.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.LoginForm;
import pixelware.model.User;
import pixelware.service.IUserService;
import pixelware.validators.LoginValidator;

/*
 * Controlador para el login de usuario
 */
@Controller
public class LoginController {
	@Autowired
	private IUserService service;
	@Autowired
	private LoginValidator validator;
	
	//Añadimos el validador
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	/*
	 * Método de negocio para mostrar formulario de inicio de sesión
	 */
	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginUser", new LoginForm());
		return model;
	}
	
	/*
	 * Método de negocio para recoger los datos del formulario
	 * Validar campos y comprobar que el usuario es correcto
	 */
	@PostMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("loginUser") @Validated LoginForm loginUser, BindingResult result, HttpServletResponse response, HttpSession session) {
		ModelAndView model = new ModelAndView();
		//Si los campos no son correctos según la validación, lo devolvemos a la página de login y mostramos los errores
		if (result.hasErrors()) {
			model.setViewName("login");
		} else {
			//Si los campos se han completado correctamente hacemos la petición a la base de datos
			User user = null;
			try {
				//Enviamos con el servicio los datos de email y contraseña
				user = service.getUser(loginUser.getEmail(), loginUser.getPassword());
				//Añadimos el usuario a la sesión actual
				session.setAttribute("user", user);
				//Y redireccionamos a la página de inicio
				try {
					response.sendRedirect("/");
					return null;
				} catch (IOException e) {
					model.setViewName("error");
					model.addObject("message", "Ha habido un error desconocido.");
				}
			} catch (EmptyResultDataAccessException e) {
				//Si devuelve la excepción es porque no hay ningún usuario que coincida con los campos
				//Enviamos el mensaje de aviso
				model.addObject("message", "Usuario o contraseña incorrectas");
				//El objeto loginUser es el mismo que ha mandado el usuario
				model.addObject("loginUser", loginUser);
				//Y devolvemos al login
				model.setViewName("login");
			}
		}
		return model;
	}
}