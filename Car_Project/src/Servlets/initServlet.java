package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.car.Car;
import com.inventory.Inventory;
import com.user.Address;
import com.user.Contact;
import com.user.Seller;
import com.user.User;
import com.users.Users;

/**
 * Servlet implementation class initServlet
 */
public class initServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public initServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("Servlet");
		
		
		//Initially loading everying from stored files to the session
		HttpSession session = request.getSession(true);
		
		//If inventory never created then created inventory
		
		if(session.getAttribute("inventory") == null) {
			System.out.println("This is running");			/*
			 * System.out.println("inside");
			 */
			Inventory inventory = new Inventory();


			 

			
			 Users users = new Users();
			 
			
			inventory.setUsers(users);
			inventory.setSearchedItem(false);
			inventory.addSeller();
			inventory.addSellerCars();
			
			inventory.addBuyer();
			inventory.addBuyerCars();
			inventory.addCarTypes();
			inventory.loadTransact();

			session.setAttribute("inventory", inventory);
		}
		
		
		// TODO Auto-generated method stub
		RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
		rs.forward(request,response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
