package controller;

import java.io.IOException;



import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/register")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDao = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/registration.jsp");
	      dispatcher.forward(request, response);*/
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean isPremium = Boolean.parseBoolean(request.getParameter("isPremium"));
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		User user =new User(username,isPremium,name,password,imageUrl);
		
		try {
			userDao.insertUser(user);
			response.sendRedirect("account-overview");
		}catch(Exception e) {
			e.printStackTrace();
		}
		 
		 response.sendRedirect("account-overview");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean isPremium = Boolean.parseBoolean(request.getParameter("isPremium"));
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		User user =new User(username,isPremium,name,password,imageUrl);
		
		
		try {
			Boolean isUserThere =userDao.isAlreadyExists(user);
			System.out.println("Is user there " +isUserThere);
			if(isUserThere) {
				 request.setAttribute("errorMessage", "User with this username already exists.");
				 
				  RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		          dispatcher.forward(request, response);
		          return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			 request.setAttribute("errorMessage", "User Registration failed.");
			 
			  RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
	          dispatcher.forward(request, response);
	          return;
		}
		
		try {
			userDao.insertUser(user);
			Cookie loginCookie = new Cookie("username",username);
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(60*60*24);
			response.addCookie(loginCookie);
        	
        	
            response.sendRedirect("index");
		}catch(Exception e) {
			e.printStackTrace();
			 request.setAttribute("errorMessage", "User Registration failed.");
			 
			  RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
	          dispatcher.forward(request, response);
	          return;
			
		}
		//doGet(request, response);
	}

}
