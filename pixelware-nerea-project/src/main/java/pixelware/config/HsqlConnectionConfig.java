package pixelware.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class HsqlConnectionConfig {
	
	/*
	 * Método para devolver la conexión de HSQL creando la tabla en memoria
	 */
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("classpath:/db/createTable.sql")
			.build();
	}
	
	/*
	 * Método para devolver la plantilla JDBC con el DataSource creado
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.setResultsMapCaseInsensitive(true);
		return template;
	}
}
