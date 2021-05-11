package com.user;

public class Contact {
	private String email,phone;

	public Contact() {
		super();
	}

	public Contact(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return String.format("%s %s", email,phone);
	}
}
