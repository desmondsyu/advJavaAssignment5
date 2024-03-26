package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.info.OracleInfo;

public class UpdateTableStaticSQL {
	
	// use static SQL to delete
	public static void main(String[] args) {
		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_DB);
			System.out.println("driver is loaded...\n");
			
			// connection to database
			Connection connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASSWORD);
			System.out.println("database is connected...\n");
			
			// query
			Statement statement = connection.createStatement();
 			statement.executeQuery("DELETE FROM country WHERE country = 'CANADA'");

			// result
			System.out.println("Delete successful ...\n");
		}
		catch (SQLException se) {
			se.getStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
		
	}
}
