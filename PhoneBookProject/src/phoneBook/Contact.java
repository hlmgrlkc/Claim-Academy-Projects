package phoneBook;

public class Contact {

	// Creating variables

	private String telephone;
	private String cell;
	private String email;
	private String home;
	private String fax;

	public Contact() {

	}

	public Contact(String telephone) {

		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
 // Formated string to used output Contact Variables 
	
	public String originalFormat() {
		return String.format("tel:%s|cell:%s|email:%s|home:%s|fax:%s", this.telephone,this.cell,this.email,this.home,this.fax) ;
		
		
	}
	// Formated string to used output Contact Variables


	@Override
	public String toString() {
		return String.format("Telephone:%s Cell Phone:%s Email:%s Home:%s Fax:%s", this.telephone, this.cell,
				this.email, this.home, this.fax);
		
	}
}
	

