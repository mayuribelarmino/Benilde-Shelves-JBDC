package com.benildeshelves.rest.book.service;

import java.util.List;

import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Status;

public interface StatusService {

	public List<Status> findAll();
	public void add(Status status);
	public List<Status> findByName(String Availability);
	public Status find(Integer statusID);
}
