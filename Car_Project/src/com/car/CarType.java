package com.car;

import java.util.List;
import java.util.TreeSet;

//This is for getting car make and models from cvs file from online
public class CarType {
	String make;
	TreeSet<String> models = new TreeSet<>();
	
	public CarType() {
		
	}
	
	public void addModel(String model) {
		models.add(model);
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public TreeSet<String> getModel() {
		return models;
	}
	public void setModel(TreeSet<String> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return "CarType [make=" + make + ", models=" + models + "]";
	}
	
	
	
}
