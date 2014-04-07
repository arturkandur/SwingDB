package Database;

/**
 * Define object Person
 * @author KArt
 *
 */
public class Person {
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	private Integer building;
	private Integer flat;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getBuilding() {
		return building;
	}
	public void setBuilding(Integer building) {
		this.building = building;
	}
	public Integer getFlat() {
		return flat;
	}
	public void setFlat(Integer flat) {
		this.flat = flat;
	}
	
	/**
	 * Check if person empty. 
	 * if it has one more fields not null - return false 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (firstName != null)
			return false;
		if (lastName != null)
			return false;
		if (city != null)
			return false;
		if (street != null)
			return false;
		if (building != null)
			return false;
		if (flat != null)
			return false;
		return true;
	}
	
	
}
