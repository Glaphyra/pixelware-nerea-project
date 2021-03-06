package pixelware.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email, password, country;
	private LocalDate birthDate;
	private List<ApixuEntry> history;
	
	public User() {}
	public User(Integer id, String email, String password, String country, LocalDate birthDate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.country = country;
		this.birthDate = birthDate;
		this.history = new ArrayList<>();
	}
	public User(RegisterForm registerForm) {
		this.id = 0;
		this.email = registerForm.getEmail();
		this.password = registerForm.getPassword();
		this.country = registerForm.getCountry();
		this.birthDate = LocalDate.of(
			Integer.parseInt(registerForm.getYear()),
			Integer.parseInt(registerForm.getMonth()),
			Integer.parseInt(registerForm.getDay())
		);
		this.history = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public List<ApixuEntry> getHistory() {
		return history;
	}
	public void setHistory(List<ApixuEntry> history) {
		this.history = history;
	}
	public void addHistory(ApixuEntry history) {
		this.history.add(history);
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}