package com.benildeshelves.rest.book.dao;

import java.util.List;
import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Status;

public interface BookDao {
		
	public List<Book> findAll();

	public Book find(Integer bookID);

	public List<Book> findByName(String Title, String Author, String ISBN, String Subject, String status);
	public List<Book> findByTitle(String Title);

	public void add(Book book);
	
	public void updatebook (Integer bookID);
	public void updatebook1 (Integer bookID);

	public void update(Book book);

	public void delete(Integer bookID);
	
	
	

}
