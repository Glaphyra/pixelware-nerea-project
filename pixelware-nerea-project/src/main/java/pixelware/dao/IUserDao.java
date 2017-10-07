package pixelware.dao;

import java.text.ParseException;

import org.springframework.dao.EmptyResultDataAccessException;

import pixelware.model.User;

public interface IUserDao {
	public int addUser(User user) throws ParseException;
	public User getUser(String email, String password) throws EmptyResultDataAccessException;
}