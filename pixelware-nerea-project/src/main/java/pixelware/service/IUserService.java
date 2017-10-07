package pixelware.service;

import java.text.ParseException;

import org.springframework.dao.EmptyResultDataAccessException;

import pixelware.model.User;

public interface IUserService {
	public int addUser(User user) throws ParseException;
	public User getUser(String email, String password) throws EmptyResultDataAccessException;
}