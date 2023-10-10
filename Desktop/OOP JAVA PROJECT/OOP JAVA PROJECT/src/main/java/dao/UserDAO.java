package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Servlet;
import db.DbConnect;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import model.User;

public class UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/vithusaynini?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	 private DbConnect dbConnect;
	 
	public UserDAO() {
		this.dbConnect = new DbConnect();
	}
	
	
		/**
		 * @see Servlet#init(ServletConfig)
		 */
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			this.dbConnect = new DbConnect();
			
		}

	private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (username,password,isPremium,name,profileImageUrl) VALUES "
			+ " (?, ?, ?,?,?);";

	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		int result;
		// try-with-resource statement will auto close the connection.
		try (Connection connection =   dbConnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setBoolean(3, user.getIsPremium());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, user.getProfileImageUrl());
			
			System.out.println(preparedStatement);
			
			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public Boolean isAlreadyExists(User user) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection =   dbConnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM user WHERE username=? LIMIT 1"
					)) {
			preparedStatement.setString(1, user.getUsername());			
			System.out.println(preparedStatement);
			
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			return true;
		}
	}

}
