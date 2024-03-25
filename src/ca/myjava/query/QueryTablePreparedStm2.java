package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import oracle.info.OracleInfo;


public class QueryTablePreparedStm2 {
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
			
			String sql = "SELECT * FROM country WHERE lifeexpectancy BETWEEN ? AND ?";
            pstmt = connection.prepareStatement(sql);

			System.out.println("Prepared statement created...");
			
			// read input of where conditions
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Input min age: ");
			int minAge = scan.nextInt();

			System.out.println("Input max age: ");
			int maxAge = scan.nextInt();

			pstmt.setInt(1, minAge);
			pstmt.setInt(2, maxAge);
			
			System.out.println("data accepted ...\n");

			// execute query
			ResultSet resultSet = pstmt.executeQuery();
			
			// print output
			System.out.printf("Country\t\tLife Expectancy\n");
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2));
			}
			
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
