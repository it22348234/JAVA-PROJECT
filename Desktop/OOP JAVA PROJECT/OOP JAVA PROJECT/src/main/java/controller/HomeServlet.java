package controller;

import db.DbConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.TVSeries;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/index")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DbConnect dbConnect;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.dbConnect = new DbConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection connection = dbConnect.getConnection();
		String username = AccountOverviewServlet.getUsername(request);
		Boolean isPremium = false;
		String name =null;
		List <TVSeries > tvSerries = new ArrayList < > ();
		System.out.println(username);
	     try {
	    	    String sqlUser = "SELECT name,isPremium,profileImageUrl FROM user WHERE username=? LIMIT 1";
	    	    PreparedStatement preparedStatement = connection.prepareStatement(sqlUser);
	    	    preparedStatement.setString(1, username);
	    	    ResultSet rs = preparedStatement.executeQuery();
    
	    	    if (rs.next()) {
	    	    	isPremium = rs.getBoolean("isPremium");
	    	    	name = rs.getString("name");
	    	    	
	    	       // profileImageUrl = rs.getString("profileImageUrl");
	    	    }

	    	   rs.close();
	    	    preparedStatement.close();
	    	    
	    	 
	    	    
	    	    String sqlIndex = "SELECT * FROM tv_series";
	    	    PreparedStatement preStaIndex = connection.prepareStatement(sqlIndex);
	    	    ResultSet rs1 = preStaIndex.executeQuery();
	    	    
	    	    while (rs1.next()) {
	                int id = rs1.getInt("id");
	                String mainUrl = rs1.getString("main_banner");
	                String videoLink = rs1.getString("video_link");
	                String title = rs1.getString("title");
	                String category = rs1.getString("category");
	                String des = rs1.getString("description");
	                tvSerries.add(new TVSeries(id,mainUrl,videoLink,title,category,des));
	            }
	    	    
	    	    
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     
	     
		 request.setAttribute("isPremium", isPremium);
		 request.setAttribute("name", name);
		 request.setAttribute("data", tvSerries);
	     //request.setAttribute("profileImageUrl", profileImageUrl);
	        // Forward the request to the JSP page for rendering
	     request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
