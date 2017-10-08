package pixelware.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignorar las demás propiedades recibidas de JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApixuEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private Location location;
	private Current current;
	
	public ApixuEntry() {}
	public ApixuEntry(Location location, Current current) {
		this.location = location;
		this.current = current;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Current getCurrent() {
		return current;
	}
	public void setCurrent(Current current) {
		this.current = current;
	}
}