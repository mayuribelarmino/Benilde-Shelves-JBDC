package com.benildeshelves.rest.book.domain;

import java.sql.Date;

public class Book {
	
	Integer bookID;
	private String Title;
	private String Author;
	private String ISBN;
	private String Subject;
	private String status;
	private Date datePublished;
	private Date dateAcquired;
	
	public Book() {
		
	}
	
	public Book(String Title, String Author, String ISBN, String Subject, Date datePublished, Date dateAcquired,String status)
	{
		this(null,Title, Author, ISBN,  Subject, datePublished, dateAcquired, status);
	}

	
	
	public Book(Integer bookID, String Title, String Author, String ISBN, String Subject, Date datePublished, Date dateAcquired, String status) {
		this.bookID = bookID;
		this.Title = Title;
		this.Author = Author;
		this.ISBN = ISBN;
		this.Subject = Subject;
		this.status = status;
		this.datePublished = datePublished;
		this.dateAcquired = dateAcquired;
	}
	
	public Integer getbookID() {
		return bookID;
	}

	public void setbookID(Integer bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String Title) {
		this.Title = Title;
	}
	
	public String getAuthor() {
		return Author;
	}
	
	public void setAuthor(String Author) {
		this.Author = Author;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getSubject() {
		return Subject;
	}
	
	public void setSubject(String Subject) {
		this.Subject = Subject;
	}
	
	public String getstatus() {
		return status;
	}
	public void setstatusID(String status) {
		this.status = status;
	}

	public Date getdatePublished() {
		return datePublished;
	}
	
	public void setdatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
	
	public Date getdateAcquired() {
		return dateAcquired;
	}
	
	public void setdateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}
	
	

}
