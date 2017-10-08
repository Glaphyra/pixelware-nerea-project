package pixelware.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignorar las demás propiedades recibidas de JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name, country;

	public Location() {}
	public Location(String name, String country) {
		this.name = name;
		this.country = country;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}