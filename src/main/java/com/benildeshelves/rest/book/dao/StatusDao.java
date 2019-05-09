package com.benildeshelves.rest.book.dao;

import java.util.List;

import com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Status;

public interface StatusDao {

	public List<Status> findAll();
	
	public List<Status> findByName(String Availability);
	public void add(Status status);
	public Status find(Integer statusID);
}
