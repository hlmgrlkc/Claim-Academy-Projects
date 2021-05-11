package phoneBook;

public class Address {

	// Creating Variables

	private String street;
	private String state;
	private String city;
	private String zipcode;

	// Creating default and parameterize constructor

	public Address() {

	}

	public Address(String street, String city, String state, String zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	// Getter and Setters for variables

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	// Formated string to used output Contact variables

	public String originalFormat() {

		return String.format("Street : %s City:%s State:%s Zipcode:%s", street, city, state, zipcode);

	}

	@Override
	public String toString() {
		return String.format("Street: %s City:%s State:%s Zipcode:%s", street, city, state, zipcode);
	}
}