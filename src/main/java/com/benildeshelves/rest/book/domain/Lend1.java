package com.benildeshelves.rest.book.domain;

import java.sql.Date;

public class Lend1 {
	
	Integer lendID;
	int bookID;
	private String borrowerName;
	private Date dateBorrowed;
	private Date dateDue;
	private Date dateReturned;
	private String lendStatus;
	private String Title;
	
	public Lend1() {
		
	}
	
	public Lend1(int bookID, String borrowerName, Date dateBorrowed,Date dateDue, Date dateReturned, String lendStatus, String Title)
	{
		this(null,bookID, borrowerName,   dateBorrowed, dateDue, dateReturned, lendStatus, Title);
	}

	
	
	public Lend1(Integer lendID, int bookID, String borrowerName, Date dateBorrowed, Date dateDue, Date dateReturned, String lendStatus, String Title) {
		this.lendID=lendID;
		this.bookID = bookID;
		this.borrowerName = borrowerName;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.dateReturned = dateReturned;
		this.lendStatus = lendStatus;
		this.Title = Title;
	}
	
	public Integer getlendID() {
		return lendID;
	}

	public void setlendID(Integer lendID) {
		this.lendID = lendID;
	}
	public int getbookID() {
		return bookID;
	}

	public void setbookID(int bookID) {
		this.bookID = bookID;
	}

	public String getborrowerName() {
		return borrowerName;
	}
	
	public void setborrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	

	public Date getdateBorrowed() {
		return dateBorrowed;
	}
	public void setdateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}
	
	public Date getdateDue() {
		return dateDue;
	}
	
	public void setdateDue(Date dateDue) {
		this.dateDue = dateDue;
	}
	
	public Date getdateReturned() {
		return dateReturned;
	}
	
	public void setdateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	
	public String getlendStatus() {
		return lendStatus;
	}
	
	public void setlendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void Title(String Title) {
		this.Title = Title;
	}
	
	

}
