package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.car.Car;
import com.inventory.Inventory;
import com.user.Address;
import com.user.Contact;
import com.user.Seller;
import com.user.User;

/**
 * Servlet implementation class SellerCarServlet
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/SellerCarServlet")
public class SellerCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path ="D:\\users\\Desktop\\Java\\Car_Project\\WebContent\\images\\"; 

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * This is for add car/seller and getting image file from uploaded file
		 */		
		Part filePart = request.getPart("imageFile"); // Retrieves <input type="file" name="file">
		
		HttpSession session = request.getSession(true);
		Inventory inventory = (Inventory) session.getAttribute("inventory");
		
		
		
		//Getting all data from jsp form
		String newSeller = request.getParameter("isNewSeller");
		String sellerEmail = request.getParameter("seller");
		
		
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		int year = Integer.parseInt(request.getParameter("year"));
		int odometer = Integer.parseInt(request.getParameter("odometer"));
		String descp = request.getParameter("descp");
		double price = Double.valueOf(request.getParameter("price"));
		
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    
	    
	  //Car toyota = new Car("Toyota","Carrola",2014,10000,5500.50);
		//Car bwm = new Car("Bwn","X5",2005,500,1200);
		
		//Date date = new Date();
		//toyota.setDateOfAdded(new Date());
		//bwm.setDateOfAdded(new Date());
		//List<Car> cars = new ArrayList<>();
		
		//cars.add(toyota);
		//cars.add(bwm);
		
		//System.out.println(toyota);
		/*
		 * System.out.println("Hello"); 
		 * 
		 */
	    
	    //Adding car
	    Car car = new Car(make,model,year,odometer,price);
	    car.setDescp(descp);
	    Date date = new Date();
	    car.setDateOfAdded(date);
	    
    	File uploads = new File(path);

	    
	    //System.out.println(inventory.getSellers().size());
    	
    	
	    if(newSeller == null) {
	    	
	    	//If seller already exist then just add car to seller cars list
	    	if(inventory.getSellers() != null) {
		    	for(Seller seller: inventory.getSellers()) {
		    		if(seller.getContact().getEmail().equals(sellerEmail)) {
		    			if(!fileName.equals("")) {
		    					    				
		    		    	
		    		    	
		    		    	car.saveImage(uploads, fileName, filePart);
		    		    	
		    		    	//filePart.write(path+seller.getFirstName()+"-"+seller.getLastName()+"-"+fileName);
		    		    	
		    		    	
		    			    
		    		    }
		    			seller.addCar(car);
		    		};
		    	}
	    	}
	    } else {
	    	
	    	//If it is new seller just register to new seller and add car to seller list
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String cellPhone = request.getParameter("cellPhone");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipCode = request.getParameter("zipCode");
			
	    	
	    	User seller = new Seller(new
	    			 Address(street,city,state,zipCode) ,new
	    			 Contact(email,cellPhone),firstName,lastName);
	    	
	    	
	    	if(!fileName.equals("")) {
		    	//filePart.write(path+seller.getFirstName()+"-"+seller.getLastName()+"-"+fileName);
		    	//car.setImgUrl("./images/"+seller.getFirstName()+"-"+seller.getLastName()+"-"+fileName);
			    //InputStream fileContent = filePart.getInputStream();
				
		    	car.saveImage(uploads, fileName, filePart);
		    	
		    	
		    }
	    	seller.addCar(car);
	    	inventory.getUsers().addUser(seller);
	    	inventory.getSellers().add((Seller) seller);
	    }
	    
    	inventory.getSellerCars().add(car);

	    
		 
	    
    	//request.getRequestDispatcher("index.jsp").forward(request, response);

    	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    	
	    
	    
	
	}

}
