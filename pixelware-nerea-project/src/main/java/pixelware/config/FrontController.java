package pixelware.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Sin clases de configuración no web
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {};
	}

	/*
	 * Clase de configuración web
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class, HsqlConnectionConfig.class};
	}

	/*
	 * Página principal /
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}