package pixelware.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pixelware.model.ApixuEntry;
import pixelware.model.City;
import pixelware.model.User;
import pixelware.service.IUserService;

/*
 * Controlador principal de la aplicación
 */
@Controller
public class MainController {
	/*
	 * Recuperar el bean de servicios
	 */
	@Autowired
	private IUserService service;
	
	/*
	 * Método de negocio para la página principal
	 */
	@GetMapping({"/", "/inicio"})
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
				//En caso de error al redireccionar
				model.setViewName("error");
				model.addObject("alert", "Ha habido un error desconocido.");
				return model;
			}
		} else {
			//Si hay un usuario, cargamos la página principal
			model.setViewName("index");
			//Añadimos al modelo una instancia para el formulario
			model.addObject("city", new City());
			//Recuperamos el usuario de la sesión
			User currentUser = (User) session.getAttribute("user");
			try{
				//Añadimos el historial en formato Json al modelo
				ObjectMapper getJson = new ObjectMapper();
				String jsonHistory = getJson.writeValueAsString(currentUser.getHistory());
	        	//Cambiamos las comillas dobles de java por las comillas dobles de HTML para que angular lo lea bien
	        	model.addObject("history",jsonHistory.replace("\"", "&quot;"));
			} catch (JsonProcessingException e) {
	        	model.addObject("alert", "No hemos podido cargar el historial.");
			}
			return model;
		}
	}
	
	/*
	 * Método de negocio para, obteniendo una ciudad de un formulario, llamar al servicio web Rest y obtener la temperatura
	 */
	@PostMapping("/getDegrees")
	public ModelAndView getDegrees(@ModelAttribute("city") City city, HttpServletResponse resp, HttpSession session) {
		ModelAndView model = new ModelAndView();
		//Si no hay sesión, devolvemos a login
		if (session.getAttribute("user") == null) {
			try {
				resp.sendRedirect("/login");
				return null;
			} catch (IOException e) {
				//Si hubiera un error al redireccionar
				model.setViewName("error");
				model.addObject("alert", "Ha habido un error desconocido.");
				return model;
			}
		} else {
			//Objeto para obtener, del SWRest, solo los datos de ciudad, pais y temperatura
	        ApixuEntry weather = null;
	        try {
	        	//Hacemos la petición al SW
	        	weather = new RestTemplate().getForObject("http://api.apixu.com/v1/current.json?key=476ab75d30bd45dcacf123553170610&q=" + city.getName(), ApixuEntry.class);
	    		//Si obtenemos un resultado, lo añadimos a la base de datos y al usuario de la sesión
	        	User currentUser = (User) session.getAttribute("user");
	        	int result = service.addHistory(currentUser.getId(), weather);
	        	//Si el resultado es mayor que cero es porque se ha añadido correctamente a la base de datos
	        	if (result > 0)
	        		currentUser.addHistory(weather);
	        	else
	        		model.addObject("alert", "No hemos podido guardar el resultado de la búsqueda en tu historial.");
	        	//También lo añadimos al modelo para mostrar el último en pantalla (y no cambiar la configuración previa)
	        	model.addObject("weather", weather);
	        	//Para trabajar con Angular, enviamos el historial en formato JSON
	        	ObjectMapper getJson = new ObjectMapper();
	        	String jsonHistory = getJson.writeValueAsString(currentUser.getHistory());
	        	//Cambiamos las comillas dobles de java por las comillas dobles de HTML para que angular lo lea bien
	        	model.addObject("history",jsonHistory.replace("\"", "&quot;"));
	    		model.setViewName("result");
	        } catch (Exception e) {
	        	//Si no obtenemos resultados, lo indicamos y devolvemos al index
	    		model.setViewName("index");
	        	model.addObject("alert", "No hemos encontrado ningún resultado para esa búsqueda.");
	        }
			return model;
		}
	}
	
	/*
	 * Método de negocio para mostrar todo el historial
	 */
	@GetMapping("/history")
	public ModelAndView getHistory(HttpServletResponse resp, HttpSession session) {
		ModelAndView model = new ModelAndView();
		//Si no hubiera sesión redireccionamos al login
		if (session.getAttribute("user") == null) {
			try {
				resp.sendRedirect("/login");
				return null;
			} catch (IOException e) {
				//En caso de error al redireccionar
				model.setViewName("error");
				model.addObject("alert", "Ha habido un error desconocido.");
				return model;
			}
		} else {
			model.setViewName("history");
			//Recuperamos el usuario de la sesión
			User currentUser = (User) session.getAttribute("user");
			try {
				//Devolvemos el historial en formato Json
				ObjectMapper getJson = new ObjectMapper();
				String jsonHistory = getJson.writeValueAsString(currentUser.getHistory());
	        	//Cambiamos las comillas dobles de java por las comillas dobles de HTML para que angular lo lea bien
	        	model.addObject("history",jsonHistory.replace("\"", "&quot;"));
			} catch (JsonProcessingException e) {
				//Si hubiera un error al cargar Json
	        	model.addObject("alert", "No hemos podido cargar el historial.");
			}
			return model;
		}
	}
	
	/*
	 * Método de negocio para cerrar sesión
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletResponse resp, HttpSession session) {
		//Destruimos la sesión
		session.invalidate();
		try {
			//Y redireccionamos al login
			resp.sendRedirect("/login");
			return null;
		} catch (IOException e) {
			//Si hubiera algún error al redireccionar
			ModelAndView model = new ModelAndView("error");
			model.addObject("alert", "Ha habido un error desconocido.");
			return model;
		}
	}
}