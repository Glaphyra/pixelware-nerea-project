package pixelware.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pixelware.model.ApixuEntry;
import pixelware.model.City;
import pixelware.model.User;

/*
 * Controlador principal de la aplicación
 */
@Controller
public class MainController {
	
	/*
	 * Método de negocio para la página principal
	 */
	@GetMapping("/")
	public ModelAndView getIndex(HttpServletResponse resp, HttpSession session) {
		//Recogemos el objeto user de la sesión
		User user = (User)session.getAttribute("user");
		ModelAndView model = new ModelAndView();
		if (user == null) {
			//Si no hay sesión, redireccionamos a la página de login
			try {
				resp.sendRedirect("/login");
				return null;
			} catch (IOException e) {
				model.setViewName("error");
				model.addObject("message", "Ha habido un error desconocido.");
				return model;
			}
		} else {
			//Si hay un usuario, cargamos la página principal
			model.setViewName("index");
			model.addObject("city", new City());
			return model;
		}
	}
	
	/*
	 * Método de negocio para, obteniendo una ciudad de un formulario, llamar al servicio web Rest y obtener la temperatura
	 */
	@PostMapping("/getDegrees")
	public ModelAndView getDegrees(@ModelAttribute City city) {
		//Objeto para obtener, del SWRest, solo los datos de ciudad, pais y temperatura
        ApixuEntry weather = null;
		ModelAndView model = new ModelAndView();
        try {
        	//hacemos la petición al SW, si obtenemos un éxito, añadimos el resultado al modelo
        	weather = new RestTemplate().getForObject("http://api.apixu.com/v1/current.json?key=476ab75d30bd45dcacf123553170610&q=" + city.getName(), ApixuEntry.class);
    		model.addObject("weather", weather);
    		model.setViewName("result");
        } catch (Exception e) {
        	//si no obtenemos resultados, lo indicamos y devolvemos al index
    		model.setViewName("index");
        	model.addObject("alert", "No hemos encontrado ningún resultado para esa búsqueda");
        }
		return model;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletResponse resp, HttpSession session) {
		session.removeAttribute("user");
		try {
			resp.sendRedirect("/login");
			return null;
		} catch (IOException e) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("message", "Ha habido un error desconocido.");
			return model;
		}
	}
}