package com.benildeshelves.rest.book.domain;

import java.sql.Date;

public class Lend {
	
	Integer lendID;
	private String bookID;
	private String borrowerName;
	private Date dateBorrowed;
	private Date dateDue;
	private Date dateReturned;
	private String lendStatus;
	
	public Lend() {
		
	}
	
	public Lend(String bookID, String borrowerName, Date dateBorrowed,Date dateDue, Date dateReturned, String lendStatus)
	{
		this(null,bookID, borrowerName,   dateBorrowed, dateDue, dateReturned, lendStatus);
	}

	
	
	public Lend(Integer lendID, String bookID, String borrowerName, Date dateBorrowed, Date dateDue, Date dateReturned, String lendStatus) {
		this.lendID=lendID;
		this.bookID = bookID;
		this.borrowerName = borrowerName;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.dateReturned = dateReturned;
		this.lendStatus = lendStatus;
	}
	
	public Integer getlendID() {
		return lendID;
	}

	public void setlendID(Integer lendID) {
		this.lendID = lendID;
	}
	public String getbookID() {
		return bookID;
	}

	public void setbookID(String bookID) {
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
	

}
