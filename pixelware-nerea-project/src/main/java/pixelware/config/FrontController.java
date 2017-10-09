package pixelware.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*
	 * Clase de configuración de base de datos
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {HsqlConnectionConfig.class};
	}

	/*
	 * Clase de configuración web
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	/*
	 * Página principal /
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}