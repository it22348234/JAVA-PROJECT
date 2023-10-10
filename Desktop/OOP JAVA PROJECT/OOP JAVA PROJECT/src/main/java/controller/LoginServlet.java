package controller;

import java.io.IOException;

import dao.LoginDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDAO loginDao = new LoginDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	     try {
	            if (loginDao.validate(username,password)) {
	                //HttpSession session = request.getSession();
	                // session.setAttribute("username",username);
	            	Cookie loginCookie = new Cookie("username",username);
	    			//setting cookie to expiry in 30 mins
	    			loginCookie.setMaxAge(60*60*24);
	    			response.addCookie(loginCookie);
	            	
	            	
	            	
	                response.sendRedirect("index");
	            } else {
	              //  HttpSession session = request.getSession();
	                //session.setAttribute("user", username);
	            request.setAttribute("errorMessage", "Invalid Username/Password");
	   			 
	  			  RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	  	          dispatcher.forward(request, response);
	               return ;
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Invalid Username/Password");
	   			 
	  			  RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	  	          dispatcher.forward(request, response);
	        }
	}

}
