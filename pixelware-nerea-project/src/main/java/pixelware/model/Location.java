package pixelware.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignorar las demás propiedades recibidas de JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	private String name, country;

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