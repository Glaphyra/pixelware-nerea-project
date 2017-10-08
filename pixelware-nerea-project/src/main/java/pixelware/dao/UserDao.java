package pixelware.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import pixelware.model.ApixuEntry;
import pixelware.model.Current;
import pixelware.model.Location;
import pixelware.model.User;

/*
 * Clase DAO para insertar y obtener datos de la base de datos
 */
@Component
public class UserDao implements IUserDao {
	/*
	 * Obtenemos el bean de plantilla JDBC
	 */
	@Autowired
	private JdbcTemplate template;
	
	/*
	 * Método para insertar un nuevo usuario en la base de datos.
	 * Insertamos en Usuarios los campos contraseña, email, pais y fecha de nacimiento (tipo LocalDate)
	 * Devuelve el número de columnas afectadas
	 */
	@Override
	public int addUser(User user) throws ParseException {
		String source = user.getBirthDate().toString();
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
		return template.update("INSERT INTO Users(password,email,country,birthdate) VALUES (?, ?, ?, ?)", new Object[] {
			user.getPassword(), user.getEmail(), user.getCountry(), date
		});
	}
	
	/*
	 * Método para obtener un usuario cuyos campos email y contraseña coincidan con los argumentos de la función
	 * Seleccionamos todos los datos para crear el objeto User y enviamos los parámetros dentro de un array de objetos
	 * A continuación hacemos una búsqueda del historial de búsqueda del usuario y añadimos los resultados al array del usuario
	 */
	@Override
	public User getUser(String email, String password) throws EmptyResultDataAccessException {
		User user = template.queryForObject("SELECT id, password, email, country, birthDate FROM Users WHERE email=? AND password=?",
				new Object[] {email, password}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet res, int id) throws SQLException {
				//Transformamos la fecha devuelta del ResultSet (tipo Date) a Localdate
				//Obtenemos el año/mes/dia con un objeto SimpleDateFormat y obtenemos el valor numerico del resultado
				Date date = res.getDate("birthdate");
				LocalDate localdate = LocalDate.of(
					Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
					Integer.parseInt(new SimpleDateFormat("MM").format(date)),
					Integer.parseInt(new SimpleDateFormat("dd").format(date))
				);
				//Creamos el usuario con los datos devueltos por el ResultSet y lo devolvemos
				return new User(res.getInt("id"), res.getString("email"), res.getString("password"), res.getString("country"), localdate);
			}
		});
		
		List<Map<String,Object>> rows = template.queryForList("SELECT name, country, degrees FROM History WHERE FK_user = " + user.getId());
		for (Map<String,Object> row : rows)
			user.addHistory(new ApixuEntry(new Location((String)row.get("name"), (String)row.get("country")), new Current((Double)row.get("degrees"))));
		
		return user;
	}

	/*
	 * Método para insertar una búsqueda exitosa en la base de datos
	 */
	@Override
	public int addHistory(int id, ApixuEntry history) {
		return template.update("INSERT INTO History(name,country,degrees,FK_user) VALUES (?, ?, ?, ?)", new Object[] {
				history.getLocation().getName(), history.getLocation().getCountry(), history.getCurrent().getTemp_c(), id
			});
	}
}