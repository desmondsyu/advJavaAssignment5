package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class QueryTablePreparedStm2 {
	
	public static void main(String[] args) {
			
			String sql = "SELECT Country, LifeExpectancy FROM countrydata where LifeExpectancy BETWEEN ? AND ?";

		try {
			
			//LOADING THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Drivers loaded successsfully");
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//ESTABLISHING A CONNECTION
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "N01579272", "oracle");
			System.out.println("Connection is Successful");
			
			//USING PREPARED STATEMENT
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//GETTING INPUT FROM THE USERS
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the min age: ");
			int minAge = scan.nextInt();
			
			System.out.println("Enter the max age: ");
			int maxAge = scan.nextInt();
			
			ps.setInt(1, minAge);
			ps.setInt(2, maxAge);
			
			//EXCECUTING THE QUERY
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String countryName = rs.getString("Country");
				float lifeExpectancy = rs.getFloat("LifeExpectancy");	
				System.out.println("Country : " + countryName);
				System.out.println("Life Expectancy" + lifeExpectancy);
			}
			rs.close();
			ps.close();
			scan.close();
			
			
			//CLOSING THE CONNECTION
			conn.close();
			System.out.println();
			System.out.println("Connection Closed successfully");
			
			
		}catch(SQLException e) {
		    System.out.println("SQLException occurred: " + e.getMessage());
		    e.printStackTrace(); 
		}

		
		
		
	}
	
	

}
