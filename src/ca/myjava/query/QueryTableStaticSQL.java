package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryTableStaticSQL {
    
    public static void main(String[] args) {
        try {
            // Loading the driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver is loaded");
            
            // Establishing a connection
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "N01579272", "oracle");
            System.out.println("Connection is successful");
            
            // Creating the SQL query
            String sql = "SELECT * FROM countrydata WHERE LifeExpectancy BETWEEN 12 AND 83";
            
            // Creating a statement
            Statement st = conn.createStatement();
            
            // Executing the query
            ResultSet rs = st.executeQuery(sql);
            
            // Iterating through the result set and printing data
            while (rs.next()) {
                // Example: Retrieving and printing the country name and life expectancy
                String countryName = rs.getString("Country");
                float lifeExpectancy = rs.getFloat("LifeExpectancy");
                System.out.println("Country: " + countryName + ", Life Expectancy: " + lifeExpectancy);
            }
            
            // Closing resources
            rs.close();
            st.close();
            conn.close();
            System.out.println("Connection closed successfully");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}

