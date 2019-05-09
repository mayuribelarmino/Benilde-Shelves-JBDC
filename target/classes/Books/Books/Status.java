package Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status {
	// STEP1 JDBC driver name and database URL
				static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
				static final String DB_URL = "jdbc:hsqldb:file:BookDB";

				// Database credentials
				static final String USER = "username";
				static final String PASS = "password";


				public static void main(String[] args)  {
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				try {
					
					// STEP 2: Register JDBC driver
					Class.forName(JDBC_DRIVER);

					// STEP 3: Open a connection
					System.out.println("Connecting to database...");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
							
					
				
					
					
				
					
					//Creating a 'STATUS' table
					System.out.println("Creating 'STATUS' table in given database...");
					stmt = conn.createStatement();

					String sql = "CREATE TABLE STATUS "
					+"(statusID INTEGER not NULL, "
					+"Availability VARCHAR(50), "
					+" PRIMARY KEY ( statusID ))";

					stmt.executeUpdate(sql);
					
					//Inserting Records
					System.out.println("Inserting records into 'STATUS' table...");
					stmt = conn.createStatement();

					sql = "INSERT INTO STATUS " + "VALUES (1, 'Available')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO STATUS " + "VALUES (2, 'Not Available')";
					stmt.executeUpdate(sql);
				
					System.out.println("Inserted records into the 'STATUS' table...");

					// STEP 6: Query the database
					System.out.println("Creating statement from 'STATUS'...");
					stmt = conn.createStatement();
					sql = "SELECT statusID,Availability FROM STATUS";
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						// Retrieve by column name
						int statusID = rs.getInt("statusID");
						String Availability= rs.getString("Availability");
						
						// Display values
						System.out.print("statusID: " + statusID);
						System.out.print(", Availability: " + Availability);
						
						
					}

					
					rs.close();
					
				
					
//			stmt.close();
		//conn.close();

				}
				
				catch (SQLException se) {
					// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e) {
					// Handle errors for Class.forName
					e.printStackTrace();
				} finally {
					// finally block used to close resources
					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se2) {
					} // nothing we can do
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						se.printStackTrace();
					} // end finally try
				} // end try
				}
}
