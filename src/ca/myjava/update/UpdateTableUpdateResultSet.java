package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.info.OracleInfo;

public class UpdateTableUpdateResultSet {
	
	// use result set to delete
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
			
			// create statement
			String sql = "SELECT * FROM country WHERE country = ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
 			Scanner scan = new Scanner(System.in);
			System.out.println("Input country: ");
			String country = scan.nextLine();
			pstmt.setString(1, country);
		
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
			    resultSet.deleteRow();
			    connection.commit();
			    System.out.println("Delete successful ...\n");
			} else {
			    System.out.println("No records found for the given country.\n");
			}
			
			scan.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
//			    try {
//			        if (connection != null) {
//			            connection.rollback();
//			        }
//			    } catch (SQLException re) {
//			        re.printStackTrace();
//			    }
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
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
			
			try {
				if(resultSet != null) {
					resultSet.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
}
