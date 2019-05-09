package com.benildeshelves.rest.book.domain;

public class Status {
	Integer statusID;
	private String Availability;
	
	
	public Status() {
		
	}
	
	

	public Status(Integer statusID, String Availability) {
		this.statusID= statusID;
		this.Availability = Availability;
		
	}
	
	public Integer getstatusID() {
		return statusID;
	}

	public void setstatusID(Integer statusID) {
		this.statusID = statusID;
	}

	public String getAvailability() {
		return Availability;
	}
	
	public void setAvailability(String Availability) {
		this.Availability = Availability;
	}



	



	
	
}

