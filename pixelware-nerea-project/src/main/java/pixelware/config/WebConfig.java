package pixelware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * Clase de configuración web
 * Habilitar MVC
 * Paquete de búsqueda "pixelware"
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="pixelware")
public class WebConfig extends WebMvcConfigurerAdapter {
	/*
	 * Ruta de los recursos
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/WEB-INF/resources/");
	}
	
	/*
	 * Método para devolver el ViewResolver
	 * Ruta webapp/Meta-inf/views y extensión .jsp para clase JstlView
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/resources/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
}