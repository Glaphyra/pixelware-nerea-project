package pixelware.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pixelware.dao.IUserDao;
import pixelware.model.User;

/*
 * Clase servicio para insertar un usuario y buscar uno por email y contraseña
 */
@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao dao;
	
	@Override
	public int addUser(User user) throws ParseException {
		return dao.addUser(user);
	}
	
	@Override
	public User getUser(String email, String password) throws EmptyResultDataAccessException {
		return dao.getUser(email, password);
	}
}