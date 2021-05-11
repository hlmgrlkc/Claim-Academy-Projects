package com.inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.car.Car;
import com.car.CarType;
import com.user.Address;
import com.user.Buyer;
import com.user.Contact;
import com.user.Seller;
import com.user.User;
import com.users.Users;



public class Inventory {
	private List<Seller> sellers = new ArrayList<>();
	private List<Buyer> buyers = new ArrayList<>();
	private List<Car> sellerCars = new ArrayList<>();
	private List<Car> buyerCars = new ArrayList<>();
	private boolean searchedItem = false;
	private List<Transaction> transactions = new ArrayList<>();
	private List<CarType> carTypes = new ArrayList();
	private String[] states;
	private Users users;
	
	
	//Make dollar format for the price
    NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);

	private double totalProfit = 0;
	private String mostExpCar = "";
	private String mostExpCarSold = "";

	//private static final String path ="D:\\users\\Desktop\\Java\\Car_Project\\src\\com\\store\\"; 
	private static final String path ="D:\\users\\Desktop\\Java\\Car_Project\\src\\com\\store\\"; 

	
	
	public Inventory() {
		super();
	}

	public Inventory(Users users) {
		super();
		this.users = users;
	}
	
	//Adding all seller cars initial creating inventory in the session
	public void addSellerCars() {
		for(Seller seller : sellers) {
			for(Car car : seller.getCars()) {
				car.discountPrice(1);
				sellerCars.add(car);
			}
		}
	}
	
	//Adding all buyer cars initial creating inventory in the session
	public void addBuyerCars() {
		for(Buyer buyer : buyers) {
			for(Car car : buyer.getCars()) {
				buyerCars.add(car);
			}
		}
	}
	
	
	//Adding all sellers initial creating inventory in the session
	public void addSeller() {
		for(User user : users.getUsers()) {
			if(user instanceof Seller) {
				//System.out.println("Adding sellerCars if it is seller in Inventory Class");
				sellers.add((Seller) user);
			}
		}
	}
	
	//Adding all buyers initial creating inventory in the session
	public void addBuyer() {
		for(User user : users.getUsers()) {
			if(user instanceof Buyer) {
				//System.out.println("Adding sellerCars if it is seller in Inventory Class");
				//System.out.println(user);
				buyers.add((Buyer) user);
			}
		}
	}
	
	/*
	 * Getting all car and model from online and adding to the cartypes list
	 */	
	public void addCarTypes() {
		String csvFile = "https://www.epa.gov/sites/production/files/2016-07/16tstcar.csv";
		
		//Reading csv file from online
	    try {
	        URL url12 = new URL(csvFile);
	        URLConnection urlConn = url12.openConnection();
	        InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
	        BufferedReader buff = new BufferedReader(inStream);

	        String line = buff.readLine();
	        line  = buff.readLine();
	        String[] lines = line.split(",");
            //System.out.println(lines[3]);
            
            CarType carType = new CarType();
            
            //Filtering some stuff making Make first letter capital
            carType.setMake(lines[3].replace("\"", "").substring(0,1) + lines[3].toLowerCase().replace("\"", "").substring(1));
            carType.addModel(lines[4].replace("\"", ""));
            carTypes.add(carType);
            int index = 0;
	        line  = buff.readLine();
	        
	        //Adding parsed data to make and model tree list
	        while (line != null) {
	        	
	        	
	            
	            lines = line.split(",");
	            if(carTypes.get(index).getMake().equals(lines[3].replace("\"", "").substring(0,1) + lines[3].toLowerCase().replace("\"", "").substring(1))) {
	            	carTypes.get(index).addModel(lines[4].replace("\"", ""));
	            }else {
	            	index +=1;
	            	CarType cType =  new CarType();
	            	cType.setMake(lines[3].replace("\"", "").substring(0,1) + lines[3].toLowerCase().replace("\"", "").substring(1));
	            	cType.addModel(lines[4].replace("\"", ""));
	            	carTypes.add(cType);
	            }
	            
	            //System.out.println(lines[3]);
	            line = buff.readLine();
	          
	           
	           
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    
	      
	      
        
	}
	
	/*
	 * This is for selling car for the buyer also adding transactions to logs. I am
	 * using some logic if there was any newBit happen
	 */
	
	public void addTransaction(String sellerEmail,String buyerEmail, String carName,boolean isLoad,boolean isExist,boolean newBit,double bit) {
		Seller s = new Seller();
		Buyer b = new Buyer();
		Car c = new Car();
		
		
		//Basically getting seller from sellers list and getting buyer from buyers list by using instanceof and getting car from seller
		for(User user : users.getUsers()) {
			
			
			 if(isLoad) { 
				 if(user instanceof Buyer && user.getContact().getEmail().equals(buyerEmail)) { 
					 b = (Buyer) user; 
					 for(Car car : b.getCars())  {
						 if(String.format("%s-%s-%d-%d",car.getMake(),car.getModel(),car.getYear(),car.getOdometer()).replaceAll("\\s+","").equals(carName)) c = car;
						 
					 } } }
			 
			
			if(user instanceof Seller && user.getContact().getEmail().equals(sellerEmail)) {
				s = (Seller) user;
				for(Car car : s.getCars()) {
					if(String.format("%s-%s-%d-%d", car.getMake(),car.getModel(),car.getYear(),car.getOdometer()).replaceAll("\\s+","").equals(carName)) c = car;
				}
			}
			if(user instanceof Buyer && user.getContact().getEmail().equals(buyerEmail)) b = (Buyer) user;
		}
		

		
		//If I am not creating new session I am writing to logs and some logic wether user will accepts or not
		if(!isLoad) {
			
			//Making difference wether it is newbitter or just adding simple car from servlet so I can put to the logs
			
			if(newBit) {
				if(bit >= (c.getOrgPrice() * 0.9)) {
					String fileName = path + "logs.txt";
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true));
						bw.append(String.format("%s,%s,%s\r\n",sellerEmail,buyerEmail,carName));
						bw.close();
						
					} catch (IOException e) {
						System.out.println(e);
					}
				
				}
				
			}else {
				
				
				
				
				String fileName = path + "logs.txt";
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true));
					bw.append(String.format("%s,%s,%s\r\n",sellerEmail,buyerEmail,carName));
					bw.close();
					
				} catch (IOException e) {
					System.out.println(e);
				}
			
			
			}
			
		}
		
		//This is where all my transactions happing
		Transaction trans = new Transaction(s,b,c,isLoad,newBit,bit);
		
		
		//If seller accpet bit it will report to transaction log or adding to transactions
		if(newBit) {
			if(bit >= (c.getOrgPrice() * 0.9)) {
				if(c.getMake() != null) transactions.add(trans);
				buyerCars.add(c);
			}
		
		
			
			
		} else {
			if(!isLoad) {
				buyerCars.add(c);
			}
			
			if(c.getMake() != null) transactions.add(trans);
		}
		
		
	
		



		
	}
	
	/*
	 * This is loading logs file so I can know who bought car and who sold it also
	 * for reporting to transactions logs
	 */	
	public void loadTransact() {
		String fileName = path+"logs.txt";
		
		try {
			Scanner inFile = new Scanner(new File(fileName));
			while(inFile.hasNextLine()) {
				String word = inFile.nextLine();
				String[] parsed = word.split(",");
				try {
					addTransaction(parsed[0],parsed[1],parsed[2],true,false,false,0);

				}catch(Exception ex) {
					System.out.println("Empty line");
				}
			}
			
			inFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	
	//Geting total profit using buyer cars price
	
	public String getTotalProfit() {
		totalProfit = 0;
		for(Car car: buyerCars) {
			//System.out.println(car);
			totalProfit += car.getPrice();
		}
		return us.format(totalProfit);
	}
	

	//If discount true that means car is more than 120 days older
	public int getExipred() {
		int exipred = 0;
		for(Car car: sellerCars) {
			//System.out.println(car);
			if(car.isDiscount()) exipred += 1;
		}
		return exipred;
	}
	
	///Add buyer to users and buyers coming from servlet
	public void addNewBuyer(Buyer buyer) {
		users.addUser(buyer);
		buyers.add(buyer);
	}
	
	
	//Getting average getting all users(buyers/sellers) getting all their car and averaging them out
	public String getAveragePrice() {
		double total = 0;
		double count = 0;
		double maxSeller = 0;
		double maxBuyer = 0;
		for(Car car: sellerCars) {
			count += 1;
			total += car.getPrice();
			if(maxSeller < car.getPrice()) {
				maxSeller = car.getPrice();
				mostExpCar = String.format("%s | %s %s %s", us.format(maxSeller) ,car.getMake(),car.getModel(),car.getYear());

			}
		}
		for(Car car: buyerCars) {
			count += 1;
			total += car.getPrice();
			if(maxBuyer < car.getPrice()) {
				maxBuyer = car.getPrice();
				mostExpCarSold = String.format("%s | %s %s %s", us.format(maxBuyer) ,car.getMake(),car.getModel(),car.getYear());

			}
		}
		
		return us.format(total/count);
	}
	
	
	
	
	
	public List<Transaction> getTransactions() {
		
		//Getting transactiong buy date  order
		Collections.sort(transactions, Collections.reverseOrder(new Comparator<Transaction>() {
		    @Override
		    public int compare(Transaction a1, Transaction a2) {
		    	// Used -1 to reverseOrder for ASC descending using String.compareTo function
		    	return a1.getCar().getDateOfPurchase().compareTo(a2.getCar().getDateOfPurchase());
		    }
		}));
		return transactions;
	}

	
	
	public boolean isSearchedItem() {
		return searchedItem;
	}

	public void setSearchedItem(boolean searchedItem) {
		this.searchedItem = searchedItem;
	}

	public String getMostExpCar() {
		return mostExpCar;
	}
	
	public String getMostExpCarSold() {
		return mostExpCarSold;
	}
	
	public List<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<Buyer> buyers) {
		this.buyers = buyers;
	}

	public List<Car> getBuyerCars() {
		return buyerCars;
	}

	public void setBuyerCars(List<Car> buyerCars) {
		this.buyerCars = buyerCars;
	}

	public int totalCarsInStock() {
		return sellerCars.size();
	}
	
	public String getSetSearchedItemFalse() {
		List<Car> emptyList = new ArrayList<>();
		
		 this.setSearchedItem(false);
		 for(Seller seller : sellers) { 
			 seller.setSearchedCars(emptyList);
			 seller.setSearched(false);
		 }
		return "";
	}
	
	

	public List<Car> getSellerCars() {

		
		return sellerCars;
	}

	public void setSellerCars(List<Car> cars) {
		this.sellerCars = cars;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Seller> getSellers() {
		return sellers;
	}
	
	public List<Seller> getSearchedSeller() {
		List<Seller> tempSeller = new ArrayList<>();
		if(searchedItem) {
			for(Seller seller : sellers) {
				if(seller.isSearched()) {
					tempSeller.add(seller);
				}
			}
			return tempSeller;
		} else {
			return sellers;
		}
	}

	public void setSellers(List<Seller> sellers) {
		
		
		this.sellers = sellers;
	}

	public List<CarType> getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(List<CarType> carTypes) {
		this.carTypes = carTypes;
	}
	
	
	//getting all state list
	public String[] getStates() {
		String[] stateList = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
		return stateList;
	}
	
	
	

	
	
	
	
}
