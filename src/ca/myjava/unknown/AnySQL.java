package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AnySQLjava {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Oracle JDBC driver loaded...");

            // Connect to Oracle database
            conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "N01579272", "oracle");
            System.out.println("Connected to Oracle database...");

            // Read SQL command
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter SQL command: ");
            String sql = scan.nextLine();
            ps = conn.prepareStatement(sql);

            System.out.println("Prepared statement created...");

            // Execute statement
            ps.execute();

            System.out.println("Execution successful...");
            scan.close();
        } catch (InputMismatchException ime) {
            System.out.println("Input Mismatch Exception: " + ime.getMessage());
            ime.printStackTrace();
        } catch (SQLException se) {
            System.out.println("SQL Exception: " + se.getMessage());
            se.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class Not Found Exception: " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}
