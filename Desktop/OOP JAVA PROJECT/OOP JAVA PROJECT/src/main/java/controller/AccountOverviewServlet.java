package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.*;

import db.DbConnect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AccountOverviewServlet
 */
@WebServlet("/account-overview")
public class AccountOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountOverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private DbConnect dbConnect;
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

		 String name = null; // Retrieve the user's name from the database
	     String profileImageUrl = null;
	 	 String username = getUsername(request);
	     
	     Connection connection = dbConnect.getConnection();
	     try {
	    	 String sql = "SELECT name, profileImageUrl FROM user WHERE username=? LIMIT 1";
	    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	    preparedStatement.setString(1, username);
	    	    ResultSet rs = preparedStatement.executeQuery();

	    	    // Check if a record was found
	    	    if (rs.next()) {
	    	        // Retrieve the values from the ResultSet
	    	        name = rs.getString("name");
	    	        profileImageUrl = rs.getString("profileImageUrl");
	    	    }

	    	    // Close ResultSet, PreparedStatement, and Connection
	    	    rs.close();
	    	    preparedStatement.close();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     
	     
		 request.setAttribute("name", name);
	     request.setAttribute("profileImageUrl", profileImageUrl);
	        // Forward the request to the JSP page for rendering
	     request.getRequestDispatcher("account-overview.jsp").forward(request, response);
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
		String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(jsonBody);
		String sql;
		String updatedValue;
		//System.out.println(jsonNode.get("name"));
		if( jsonNode.get("name") != null) {
			 updatedValue = jsonNode.get("name").asText();
			 sql="UPDATE user SET name=? WHERE username=?";
		}else {
			updatedValue =  jsonNode.get("imageURL").asText();
			sql="UPDATE user SET profileImageUrl=? WHERE username=?";
		}
		
		String username = getUsername(request);
		Connection connection = dbConnect.getConnection();
	    int result;
		// try-with-resource statement will auto close the connection.
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, updatedValue);
				preparedStatement.setString(2,username );
					
				System.out.println(preparedStatement);
					
				result=preparedStatement.executeUpdate();
				 response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write("{\"message\": \"Name updated successfully.\"}");
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unable to update");
			}
	   
	}
	
	public static String getUsername(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        String name = cookie.getName();
		        if("username".equals(name)) {
		        	return cookie.getValue();
		        }
		    }
		}
		return null;

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
