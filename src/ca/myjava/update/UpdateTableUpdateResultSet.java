package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.info.OracleInfo;

public class UpdateTableUpdateResultSet {

	// use result set to insert
	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_DB);
			System.out.println("driver is loaded...\n");

			// connection to database
			connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASSWORD);
			System.out.println("database is connected...\n");

			// create statement
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			resultSet = stmt.executeQuery("SELECT * FROM country");
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Input country: ");
			String country = scan.nextLine();
			System.out.println("Input life expectancy: ");
			int age = scan.nextInt();
			
			resultSet.moveToInsertRow();
			resultSet.updateString("country", country);
			resultSet.updateInt("lifeexpectancy", age);
			
			resultSet.insertRow();
			resultSet.moveToCurrentRow();

			scan.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}
}
