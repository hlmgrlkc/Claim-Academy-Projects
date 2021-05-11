package com.inventory;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.car.Car;
import com.user.Buyer;
import com.user.Seller;

public class Transaction {
	private Seller seller;
	private Buyer buyer;
	private Car car;
	private boolean accept = false;
	private boolean[] option = {true,false};
	private boolean isLoad = false;
	private boolean newBit = false;
	private double bit = 0;
    NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);

	public Transaction() {
		
	}
	
	public Transaction(Seller seller, Buyer buyer, Car car,boolean isLoad,boolean newBit,double bit) {
		super();
		this.seller = seller;
		this.buyer = buyer;
		this.car = car;
		this.isLoad = isLoad;
		this.newBit = newBit;
		this.bit = bit;
		transact();
	}
	
	/*
	 * This is for if transaction happen if seller accepts bit also adds buyer if
	 * seller accepts and removes car from sellers list
	 */	
	public void transact() {

		Date date = new Date();

		if(!isLoad) {
			/*
			 * int opt = (int) Math.round( Math.random() ); this.accept = option[opt];
			 * if(accept) {
			 * 
			 * }
			 */
			
			if(newBit) {
				if(bit >= (car.getOrgPrice() * 0.9)) {
					car.setPrice(bit);
					car.setDateOfPurchase(date);
					this.seller.removeCar(car);
					this.buyer.addCar(car);
					this.seller.override();
				}else {
					car.setUserAccept(true);
				}
			}else {
				car.setDateOfPurchase(date);
				this.seller.removeCar(car);
				this.buyer.addCar(car);
				this.seller.override();
			}
			
			
			
		}
		
		
		
		
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public boolean[] getOption() {
		return option;
	}

	public void setOption(boolean[] option) {
		this.option = option;
	}
	
	
	//Reporting transaction log
	public String toString() {
		/*
		 * if(car.isDiscount()) { //return String.
		 * format("%s %s sold %s %s %s to %s %s at %s price with discount of %d%%"
		 * ,seller.getFirstName(),seller.getLastName(),car.getMake(),car.getModel(),car.
		 * getYear(),buyer.getFirstName(),buyer.getLastName(),us.format(car.getPrice()),
		 * car.getDiscountPercent()); } else { }
		 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

		return String.format("%s|%s %s sold %s %s %s to %s %s at %s price",simpleDateFormat.format(car.getDateOfPurchase()),seller.getFirstName(),seller.getLastName(),car.getMake(),car.getModel(),car.getYear(),buyer.getFirstName(),buyer.getLastName(),us.format(car.getPrice()));

	}
	
}
