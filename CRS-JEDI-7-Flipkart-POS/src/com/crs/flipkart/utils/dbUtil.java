/**
 * 
 */
package com.crs.flipkart.utils;

import java.io.InputStream;

/**
 * @author Shubham
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbUtil {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crs_database";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static Connection conn = null;
	public static PreparedStatement stmt = null;

	public static Connection getConnection() {
		try {
			// Step 3 Regiater Driver here and create connection

			Class.forName("com.mysql.jdbc.Driver");

			// Step 4 make/open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean closeConnection() {
		try {
            if (stmt != null)
                stmt.close();
        } 
		catch (SQLException se2) {
        	return false;
        }
        try {
            if (conn != null)
                conn.close();
        } 
        catch (SQLException se) {
        	return false;
        }
        return true;
	}
}
