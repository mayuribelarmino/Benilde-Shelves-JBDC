package Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Lend {
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
	
		
		System.out.println("Creating 'LEND' table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE LEND " 
		+ "(lendID INT IDENTITY PRIMARY KEY, " 
		+"bookID INT, " 
		+ "borrowerName VARCHAR(255), "
		+"dateBorrowed DATE,"
		+"dateDue DATE,"
		+"dateReturned DATE,"
		+"FOREIGN KEY (bookID) REFERENCES BOOKS(bookID))";

		stmt.executeUpdate(sql);
		System.out.println("Created 'LEND' table in given database...");
		
		//Inserting Records
		System.out.println("Inserting records into the 'LEND' table...");
		stmt = conn.createStatement();

		sql = "INSERT INTO LEND (BookID, borrowerName, dateBorrowed, dateDue, DateReturned) "
		+ "VALUES (0, 'Mayuri Belarmino', '2019-04-17', '2019-04-22', '2019-04-24')";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO LEND (BookID, borrowerName, dateBorrowed, dateDue, DateReturned) "
		+ "VALUES (1, 'Sayuri Belarmino', '2019-02-17', '2019-02-22', '2019-02-24')";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO LEND (BookID, borrowerName, dateBorrowed, dateDue, DateReturned) "
		+ "VALUES (2, 'Leerick Bautista', '2019-01-17', '2019-01-22', '2019-01-24')";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO LEND (BookID, borrowerName, dateBorrowed, dateDue, DateReturned) "
		+ "VALUES (3, 'Gin Samonte', '2019-03-17', '2019-03-22', '2019-03-24')";
		stmt.executeUpdate(sql);
		System.out.println("Inserted records into the 'LEND' table...");

		// STEP 6: Query the database
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		sql = "SELECT lendID,bookID,BorrowerName,dateBorrowed,dateDue,dateReturned FROM LEND";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			// Retrieve by column name
			int bookID = rs.getInt("lendID");
			int lendID = rs.getInt("bookID");
			String BorrowerName = rs.getString("BorrowerName");
			Date dateBorrowed = rs.getDate("dateBorrowed");
			Date dateDue = rs.getDate("dateDue");
			Date dateReturned = rs.getDate("dateReturned");
			
			
		
			// Display values
			System.out.println("LendID: " +lendID +" " 
			+"BookID: " + bookID +" "
			+"BorrowerName: "+ BorrowerName+" "
			+"Date Borrowed: "+dateBorrowed+" "
			+"Date Due: "+dateDue+" "
			+"Date Returned: "+dateReturned);
			 
			
		}

		
		rs.close();
	
	
		
//stmt.close();
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
