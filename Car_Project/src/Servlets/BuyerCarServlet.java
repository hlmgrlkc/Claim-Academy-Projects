package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventory.Inventory;
import com.user.Address;
import com.user.Buyer;
import com.user.Contact;
import com.user.Seller;
import com.user.User;

/**
 * Servlet implementation class BuyerCarServlet
 */
@WebServlet("/BuyerCarServlet")
public class BuyerCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerCarServlet() {
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
		doGet(request, response);
		
		/*
		 * gettting session also inventroy from session
		 */		
		HttpSession session = request.getSession(true);
		Inventory inventory = (Inventory) session.getAttribute("inventory");
		
		/*
		 * Getting post data from jsp
		 */		
		String newBuyer = request.getParameter("isNewBuyer");
		String buyerEmail = request.getParameter("buyerEmail");
		String carName = request.getParameter("carName");
		String sellerEmail = request.getParameter("sellerEmail");
		
		/*
		 * if newBit is checkmark then I know that buyer wants bit
		 */		
		boolean newBit = request.getParameter("isNewBit") == null ? false : true;
		double bit = 0;
		
		
		if(newBit) bit = Double.valueOf(request.getParameter("bit"));
		
		
		
		//System.out.println(String.format("%s %s %s %s", newBuyer,buyerEmail,carName,sellerEmail));
		
		if(newBuyer == null) {
			//If it is not new buyer
			//Just add information to transaction class
			//System.out.println(carName);
	    	inventory.addTransaction(sellerEmail, buyerEmail, carName,false,true,newBit,bit);
	    } else {
	    	
	    	//If it is new User and get all user information and adding to user and inventory users list
	    	
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String cellPhone = request.getParameter("cellPhone");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipCode = request.getParameter("zipCode");
			
	    	
	    	User buyer = new Buyer(new
	    			 Address(street,city,state,zipCode) ,new
	    			 Contact(email,cellPhone),firstName,lastName);
	    	
	    	
	    	//System.out.println("Buy Logic" + buyer);
	    	inventory.addNewBuyer((Buyer) buyer);
	    	
	    	//System.out.println(carName);
	    	//Adding trasnaction
	    	inventory.addTransaction(sellerEmail, email, carName,false,false,newBit,bit);
	    }
	    
		//Redirecting to main page ones request is finished with session still intact
    	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));

	}

}
