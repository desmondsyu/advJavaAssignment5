package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import oracle.info.OracleInfo;

public class UpdateTablePreparedStm {
	
	// use prepared statement to insert value to table
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_DB);
			System.out.println("driver is loaded...\n");
			
			// connection to database
			connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASSWORD);
			System.out.println("database is connected...\n");
			
			// create statement
			String sql = "INSERT INTO country VALUES ( ?, ?)";
            pstmt = connection.prepareStatement(sql);

			System.out.println("Prepared statement created...");
			
			// read input
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Input country: ");
			String country = scan.nextLine();

			System.out.println("Input life expectancy: ");
			int age = scan.nextInt();

			pstmt.setString(1, country);
			pstmt.setInt(2, age);
			
			System.out.println("Data accepted ...\n");
			
			// execute
			pstmt.executeUpdate();
			System.out.println("Insert successful ...\n");
			
			scan.close();
		}
		catch (InputMismatchException ime) {
			ime.getStackTrace();
		}
		catch (SQLException se) {
			se.getStackTrace();
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.getStackTrace();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(connection != null) {
					connection.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
}
