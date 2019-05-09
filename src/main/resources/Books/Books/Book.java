package Books;


	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
import java.util.Date;

	
	public class Book {
		
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
						
				System.out.println("Creating 'BOOK' table in given database...");
				stmt = conn.createStatement();
				
				

				String sql = "CREATE TABLE BOOKS"
				+ "(bookID INT IDENTITY PRIMARY KEY, " 
				+ "Title VARCHAR(255),"
				+ "Author VARCHAR(255), "
				+ "ISBN VARCHAR(50)," 
				+"Subject VARCHAR(255)," 
				+"datePublished DATE,"
				+"dateAcquired DATE,"
				+"statusID INT,"
				+"FOREIGN KEY (statusID) REFERENCES STATUS(statusID))";
				

				stmt.executeUpdate(sql);
				System.out.println("Created 'BOOK' table in given database...");
				
				//Inserting Records
				System.out.println("Inserting records into the table...");
				stmt = conn.createStatement();

				sql = "INSERT INTO BOOKS (Title, Author, ISBN, Subject, datePublished, dateAcquired, statusID) "
				+ "VALUES ('1 Harry Potter and the Sorcerers Stone', 'J.K Rowling', '9781781102459', 'Fiction', '2001-11-22', '2016-11-07', 1)";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO BOOKS (Title, Author, ISBN, Subject, datePublished, dateAcquired, statusID) "
				+ "VALUES ('1 Harry Potter and The Chamber of Secrets', 'J.K Rowling', '2781781102459', 'Fiction', '2002-10-21', '2016-11-07', 2)";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO BOOKS (Title, Author, ISBN, Subject, datePublished, dateAcquired, statusID) "
				+ "VALUES ('1 Harry Potter and Prisoner of Azkaban', 'J.K Rowling', '9581781102459', 'Fiction', '2003-09-19', '2016-11-07', 1)";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO BOOKS (Title, Author, ISBN, Subject, datePublished, dateAcquired, statusID) "
				+ "VALUES ('1 Harry Potter and The Goblet of Fire', 'J.K Rowling', '9761781102459', 'Fiction', '2004-08-18', '2016-11-07', 2)";
				stmt.executeUpdate(sql);
				System.out.println("Inserted records into the 'BOOKS' table...");

				// STEP 6: Query the database
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				sql = "SELECT BookID,Title, Author, ISBN,Subject, datePublished,dateAcquired, statusID FROM BOOKS";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					// Retrieve by column name
					int BookID = rs.getInt("BookID");
					String Title = rs.getString("Title");
					String Author = rs.getString("Author");
					String ISBN= rs.getString("ISBN");
					String Subject = rs.getString("Subject");
					Date datePublished = rs.getDate("datePublished");
					Date dateAcquired = rs.getDate("dateAcquired");
					int statusID = rs.getInt("statusID");
					
				
					// Display values
					System.out.println("bookID: " + BookID+" " 
					+"Title: " + Title +" "
					+"Author: "+ Author+" "
					+"ISBN: "+ISBN+" "
					+"Subject: "+Subject+" "
					+"Date Published: "+datePublished+" "
					+"Date Acquired: " + dateAcquired +" "
					+ "StatusID: "+ statusID);
					 
					
				}

				
				rs.close();
			
				
			
				
//		stmt.close();
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
	

