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
import com.benildeshelves.rest.book.domain.Status;
import com.benildeshelves.rest.book.dao.StatusDao;;

public class StatusJbdcDaoImpl implements StatusDao{

	private static StatusJbdcDaoImpl INSTANCE;
	private JDBCDataSource dataSource;

	static public StatusJbdcDaoImpl getInstance() {

		StatusJbdcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new StatusJbdcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}
	private StatusJbdcDaoImpl() {
		init();
	}

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:LIBRARY1");
		dataSource.setUser("username");
		dataSource.setPassword("password");
		
		createStatusTable();
		insertInitStatus();
	
		
	

	}
	
	private void createStatusTable() {
		String createSql = "CREATE TABLE STATUS "
				+"(statusID INTEGER not NULL, "
				+"Availability VARCHAR(50), "
				+" PRIMARY KEY ( statusID ))";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitStatus() {

		add(new Status(1, "Available"));
		add(new Status(2, "Not Available"));
		}
	
	@Override
	public List<Status> findAll() {

		return findByName(null);
	}
	
	@Override
	public void add(Status status) {
		
		String insertSql = "INSERT INTO STATUS (statusID, Availability) VALUES (?,?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setInt(1, status.getstatusID());
			ps.setString(2, status.getAvailability());
		
		
			
			//ps.setDate(6, book.getdatePublished());
			//ps.setDate(7, book.getdateAcquired());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Status> findByName(String Availability) {
		List<Status> status1 = new ArrayList<>();

		String sql = "SELECT * FROM STATUS WHERE Availability LIKE ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(Availability));
		
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Status status = new Status(results.getInt("statusID"), results.getString("Availability"));
				status1.add(status);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return status1;
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
public Status find(Integer statusID) {

	Status status = null;
	
	if (statusID != null) {
		String sql = "SELECT * FROM STATUS where statusID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, statusID.intValue());
			ResultSet results = ps.executeQuery();

			if (results.next()) {
				status = new Status(results.getInt("statusID"),results.getString("Availability"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	return status;
}
}
