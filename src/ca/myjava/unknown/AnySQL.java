package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import oracle.info.OracleInfo;

public class AnySQL {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_MYSQL);
			System.out.println("driver is loaded...\n");
			
			// connection to database
			connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASSWORD);
			System.out.println("database is connected...\n");
			
			// read SQL command
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter SQL command: ");
			String sql = scan.nextLine();
            pstmt = connection.prepareStatement(sql);

			System.out.println("Prepared statement created...\n");
			
			// execute statement
			pstmt.execute();
			
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
