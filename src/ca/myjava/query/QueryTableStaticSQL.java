package ca.myjava.query;

import java.sql.*;
import oracle.info.OracleInfo;


public class QueryTableStaticSQL {
	public static void main(String[] args) {
		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_MYSQL);
			System.out.println("driver is loaded...\n");
			
			// connection to database
			Connection connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASSWORD);
			System.out.println("database is connected...\n");
			
			// query
			Statement statement = connection.createStatement();
 			ResultSet resultSet = statement.executeQuery("SELECT * FROM country WHERE lifeexpectancy BETWEEN 70 AND 90");
 			
			System.out.printf("Country\t\tLife Expectancy\n");
			
			// print output
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2));
			}
		}
		catch (SQLException se) {
			se.getStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
		
	}
}
