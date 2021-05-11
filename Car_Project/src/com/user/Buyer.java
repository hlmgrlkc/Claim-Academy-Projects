package com.user;

import java.util.List;

import com.car.Car;

//Buyer inhereted from user also type is buyer
public class Buyer extends User {

	public Buyer() {
		super();
		// TODO Auto-generated constructor stub
		type = "buyer";

	}

	public Buyer( Address address, Contact contact, String firstName, String lastName) {
		super(address, contact, firstName, lastName);
		type = "buyer";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Buyer [Cars=" + cars + ", address=" + address + ", contact=" + contact + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
