package com.user;

import java.util.List;

import com.car.Car;

public class Seller extends User{

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
		type = "seller";

	}

	public Seller( Address address, Contact contact, String firstName, String lastName) {
		super(address, contact, firstName, lastName);
		type = "seller";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Seller [Cars=" + cars + ", address=" + address + ", contact=" + contact + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
	
	

}
