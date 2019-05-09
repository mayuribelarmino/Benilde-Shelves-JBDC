package com.benildeshelves.rest.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Lend1;
import com.benildeshelves.rest.book.domain.Status;


public class LendJbdcDaoImpl implements LendDao {
	private static LendJbdcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	static public LendJbdcDaoImpl getInstance() {

		 LendJbdcDaoImpl  instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new  LendJbdcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private  LendJbdcDaoImpl() {
		init();
	}

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:LIBRARY1");
		dataSource.setUser("username");
		dataSource.setPassword("password");
		
		
		createLendTable();
		insertInitLend();
		
	

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
	
	private void createLendTable() {
		String createSql = "CREATE TABLE LEND " 
				+ "(lendID INT IDENTITY PRIMARY KEY, " 
				+"bookID INT, " 
				+ "borrowerName VARCHAR(255), "
				+"dateBorrowed DATE,"
				+"dateDue DATE,"
				+"dateReturned DATE,"
				+"lendStatus VARCHAR(50),"
				+"FOREIGN KEY (bookID) REFERENCES BOOK(bookID))"; 	

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitLend(){
		
		java.sql.Date a = java.sql.Date.valueOf("2019-04-17");
		java.sql.Date b = java.sql.Date.valueOf("2019-04-22");
		java.sql.Date c = java.sql.Date.valueOf("2019-04-24");
		java.sql.Date d = java.sql.Date.valueOf("2019-04-27");
	

		add(new Lend(1, "Mayuri Belarmino",a, d, c, "Not Returned"));
		
		add(new Lend(2, "Leerick Bautista",a, b, c, "Not Returned"));
	
		}

	
	@Override
	public List<Lend1> findAll() {

		return findByName(null);
	}

	@Override
	public Lend find(Integer lendID) {

		Lend lend = null;
		
		if (lendID != null) {
			String sql = "SELECT * FROM LEND where lendID = ?";
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, lendID.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					lend = new Lend(results.getInt("lendID"),results.getInt("bookID"), results.getString("borrowerName"),
							results.getDate("dateBorrowed"),
							results.getDate("dateDue"),results.getDate("dateReturned"), results.getString("lendStatus"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return lend;
	}

	@Override
	public List<Lend1> findByName(String borrowerName) {
		List<Lend1> lends1 = new ArrayList<>();

		String sql = "SELECT LEND.lendID, LEND.bookID, LEND.borrowerName, LEND.dateBorrowed, LEND.dateDue, LEND.dateReturned, LEND.lendStatus, BOOK.Title FROM LEND INNER JOIN BOOK ON LEND.bookID=BOOK.bookID"
				+ " WHERE borrowername LIKE?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(borrowerName));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Lend1 lend1  = new Lend1(results.getInt("lendID"),results.getInt("bookID"), results.getString("borrowerName"),
						results.getDate("dateBorrowed"),
						results.getDate("dateDue"),results.getDate("dateReturned"),results.getString("lendStatus"), results.getString("Title"));
				lends1.add(lend1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return lends1;
	}
	
	@Override
	public void add(Lend lend) {
		
		String insertSql = "INSERT INTO LEND (bookID,borrowerName,dateBorrowed,dateDue,dateReturned, lendStatus) VALUES (?,?,?,?,?,?) ";
		String updateSql1= "UPDATE LEND SET dateDue =DATEADD(dd,10, (SELECT TOP 1 dateBorrowed from LEND ORDER BY lendID DESC))"
				+ " WHERE lendID =(SELECT TOP 1 lendID from LEND ORDER BY lendID DESC)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setInt(1, lend.getbookID());
			ps.setString(2, lend.getborrowerName());
			ps.setDate(3,lend.getdateBorrowed());
			ps.setDate(4, lend.getdateDue());
			ps.setDate(5, lend.getdateReturned());
			ps.setString(6, lend.getlendStatus());
			ps.executeUpdate();
			
			try (Connection conn1 = dataSource.getConnection(); PreparedStatement ps1 = conn.prepareStatement(updateSql1)) {

			
				ps1.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Lend lend) {
		String updateSql = "UPDATE LEND SET bookID = ?, borrowerName = ?, dateBorrowed=?, dateDue=?,dateReturned=?, lendStatus=? WHERE lendID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setInt(1, lend.getbookID());
			ps.setString(2, lend.getborrowerName());
			ps.setDate(3, lend.getdateBorrowed());
			ps.setDate(4, lend.getdateDue());
			ps.setDate(5, lend.getdateReturned());
			ps.setString(6, lend.getlendStatus());
			ps.setInt(7, lend.getlendID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public void delete(Integer LendID) {
		String updateSql = "DELETE FROM LEND WHERE LendID = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setLong(1, LendID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	


}
