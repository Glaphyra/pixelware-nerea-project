package pixelware.model;

import java.io.Serializable;

public class RegisterForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email, password, country, day, month, year;
	
	public RegisterForm() {
		this("","","","","","");
	}
	public RegisterForm(String email, String password, String country, String day, String month, String year) {
		this.email = email;
		this.password = password;
		this.country = country;
		this.day = day;
		this.month = month;
		this.year = year;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}