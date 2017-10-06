package pixelware.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignorar las demás propiedades recibidas de JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
	private Double temp_c;

	public Double getTemp_c() {
		return temp_c;
	}

	public void setTemp_c(Double temp_c) {
		this.temp_c = temp_c;
	}
}