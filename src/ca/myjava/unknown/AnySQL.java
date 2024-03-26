package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import oracle.info.OracleInfo;

public class AnySQL {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// load driver
			Class.forName(OracleInfo.DRIVER_CLASS_DB);
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

			// show the output or execution result 
			if(sql.toLowerCase().startsWith("select")) {
				resultSet = pstmt.executeQuery();
				
				while(resultSet.next()) {
					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
				}
			}
			else {
				int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
			}
			
			System.out.println("Execute successful ...");
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
