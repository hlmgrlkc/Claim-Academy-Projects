package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.car.Car;
import com.inventory.Inventory;
import com.user.Seller;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Inventory inventory = (Inventory) session.getAttribute("inventory");
		
		RequestDispatcher rs = request.getRequestDispatcher("index.jsp");

		
		List<Car> sellerCars = inventory.getSellerCars();
		List<Seller> sellers = inventory.getSellers();
		
		String search = request.getParameter("search");
		
		inventory.setSearchedItem(false);

		String[] searchList = search.split("[^a-zA-Z0-9]+");
		List<Car> availableCars = new ArrayList<>();
		List<Car> emptyList = new ArrayList<>();
		
		 inventory.setSearchedItem(false);
		 for(Seller seller : sellers) { 
			 seller.setSearchedCars(emptyList);
			 seller.setSearched(false);
		 }
		 
		if(search == null || search.isEmpty() || search.chars().allMatch(Character::isWhitespace)) {

			 rs.forward(request,response);	
		} else {		
			
		
		System.out.println("Search: " + search);
		
		System.out.println(sellers.size());
		
		/*for(Seller seller : sellers) {
			seller.setSearchedCars(availableCars);
			seller.setCars(availableCars);
		}*/

		
		for(Car car: sellerCars) {
			String searchFormat = car.getFormatCarSearch().toLowerCase();
			boolean isContain = true;
			for(String s : searchList) {
				s = s.toLowerCase();
				if(!searchFormat.contains(s)) isContain = false;
			}
			
			if(isContain) {
				System.out.println(car.getFormatCarSearch());
				availableCars.add(car);
			}
		}
		
		
		for(Seller seller : sellers) {
			for(Car car : seller.getCars()) {
				if(availableCars.contains(car)) {
					inventory.setSearchedItem(true);
					seller.setSearched(true);
					seller.addSearchedCar(car);
					//seller.getCars().add(car);
					
				};
			}
		}
		
		
		//inventory.setSellerCars(availableCars);
		
		
		
		//response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));	}
		
		rs.forward(request,response);	
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
