package com.benildeshelves.rest.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.benildeshelves.rest.book.domain.Book;



public class BookJbdcDaoImpl implements BookDao {
	private static BookJbdcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	static public BookJbdcDaoImpl getInstance() {

		BookJbdcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new BookJbdcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private BookJbdcDaoImpl() {
		init();
	}

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:LIBRARY1");
		dataSource.setUser("username");
		dataSource.setPassword("password");
		
		
		createBookTable();
		insertInitBooks();
		
	

	}
	
	

	private void createBookTable() {
		String createSql = "CREATE TABLE BOOK"
				+ "(bookID INT IDENTITY PRIMARY KEY, " 
				+ "Title VARCHAR(255),"
				+ "Author VARCHAR(255), "
				+ "ISBN VARCHAR(50)," 
				+ "Subject VARCHAR(255)," 
				+"datePublished DATE,"
				+"dateAcquired DATE,"
				+"status VARCHAR(50))";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitBooks() {
		java.sql.Date a = java.sql.Date.valueOf("2001-11-22");
		java.sql.Date b = java.sql.Date.valueOf("2016-11-07");
		java.sql.Date c = java.sql.Date.valueOf("2001-10-21");
		java.sql.Date d = java.sql.Date.valueOf("2001-09-20");
		java.sql.Date e = java.sql.Date.valueOf("2001-08-19");
		
		
			add(new Book("Harry Potter","J.K Rowling","9781781102459", "Fiction",a,b,"Available"));
		
		
			add(new Book("Bakit Hindi Ka Crush ng Crush Mo?","Ramon Bautista","2781781102459", "Comedy",c,b,"Available"));
		
			add(new Book("The Notebook","Nicholas Sparks","9581781102459", "Romance",d,b,"Available"));
		
			add(new Book("Noli Me Tangere","Jose Rizal","9761781102459", "Horror",e,b,"Available"));
		
	}


	

	@Override
	public List<Book> findAll() {

		return findByName(null,null,null,null,null);
	}

	@Override
	public Book find(Integer bookID) {

		Book book = null;
		
		if (bookID != null) {
			String sql = "SELECT * FROM BOOK where bookID = ?";
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, bookID.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					book = new Book(results.getInt("bookID"), results.getString("Title"),
							results.getString("Author"),results.getString("ISBN"),
							results.getString("Subject"),results.getDate("datePublished"),
							results.getDate("dateAcquired"),results.getString("status"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return book;
	}

	@Override
	public List<Book> findByName(String Title, String Author, String ISBN, String Subject, String status) {
		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM BOOK WHERE Title LIKE ? AND Author LIKE ? AND ISBN LIKE ? AND Subject LIKE ? AND status LIKE ?";
		//String sql = "SELECT bookID, Title, Author, ISBN, Subject FROM BOOK WHERE Title LIKE ? AND Author LIKE ? AND ISBN LIKE ? AND Subject LIKE ?";
		//String sql="SELECT * from BOOK";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(Title));
			ps.setString(2, createSearchValue(Author));
			ps.setString(3, createSearchValue(ISBN));
			ps.setString(4, createSearchValue(Subject));
			ps.setString(5, createSearchValue(status));
			
			//ps.executeUpdate();
			
			//Statement stmt=conn.createStatement();
		//ResultSet results = stmt.executeQuery(sql);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				Book book = new Book(results.getInt("bookID"), results.getString("Title"),
						results.getString("Author"),results.getString("ISBN"),
						results.getString("Subject"),results.getDate("datePublished"),
						results.getDate("dateAcquired"),results.getString("status"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return books;
	}

	@Override
	public List<Book> findByTitle(String Title) {
		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM BOOK WHERE Title LIKE ?";
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(Title));
		
			
			Statement stmt=conn.createStatement();
		ResultSet results = stmt.executeQuery(sql);

			while (results.next()) {
				Book book = new Book(results.getInt("bookID"), results.getString("Title"),
						results.getString("Author"),results.getString("ISBN"),
						results.getString("Subject"),results.getDate("datePublished"),
						results.getDate("dateAcquired"),results.getString("status"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return books;
	}
	private String createSearchValue(String string) {
		
		String value;
		
		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}
		
		return value;
	}
	
	@Override
	public void add(Book book) {
		
		String insertSql = "INSERT INTO BOOK (Title,Author,ISBN,Subject,status,datePublished,dateAcquired) VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getSubject());
			ps.setString(5, book.getstatus());
			ps.setDate(6,book.getdatePublished());
			ps.setDate(7,book.getdateAcquired());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Book book) {
		String updateSql = "UPDATE BOOK SET Title =?, Author =?, ISBN=?, Subject=?, datePublished=?, dateAcquired=?, status=? WHERE bookID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getSubject());
			ps.setDate(5, book.getdatePublished());
			ps.setDate(6, book.getdateAcquired());
			ps.setString(7, book.getstatus());
			ps.setInt(8, book.getbookID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updatebook(Integer bookID) {
		String updateSql = "UPDATE BOOK SET status='Not Available' WHERE bookID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

		
			ps.setInt(1, bookID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void updatebook1(Integer bookID) {
		String updateSql = "UPDATE BOOK SET status='Available' WHERE bookID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

		
			ps.setInt(1, bookID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public void delete(Integer bookID) {
		String updateSql = "DELETE FROM BOOK WHERE bookID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setLong(1, bookID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	

	
}
