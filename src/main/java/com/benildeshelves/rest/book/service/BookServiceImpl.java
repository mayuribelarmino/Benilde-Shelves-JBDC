package com.benildeshelves.rest.book.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.benildeshelves.rest.book.dao.BookDao;
import com.benildeshelves.rest.book.dao.BookJbdcDaoImpl;
import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Status;

public class BookServiceImpl implements BookService{
	
	BookDao bookDao;

	public BookServiceImpl() {
		this.bookDao = BookJbdcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}
	
	

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book find(Integer bookID) {
		return bookDao.find(bookID);
	}

	@Override
	public List<Book> findByName(String Title, String Author, String ISBN, String Subject, String status) {
		return bookDao.findByName(Title, Author, ISBN, Subject, status);
	}
	
	@Override
	public List<Book> findByTitle(String Title) {
		return bookDao.findByTitle(Title);
	}

	@Override
	public void add(Book book) {
		if (validate(book)) {
			bookDao.add(book);
		} else {
			throw new IllegalArgumentException("Fields Title, Author, ISBN, and Subject cannot be null.");
		}
	}
	
	

	@Override
	public void upsert(Book book) {
		if (validate(book)) {
			if(book.getbookID() != null && book.getbookID() >= 0) {
				bookDao.update(book);
			} else {
				bookDao.add(book);
			}
		} else {
			throw new IllegalArgumentException("Fields  cannot be blank.");
		}
	}
	

	@Override
	public void updatebook(Integer bookID) {
		bookDao.updatebook(bookID);
	}
	
	@Override
	public void updatebook1(Integer bookID) {
		bookDao.updatebook1(bookID);
	}
	

	@Override
	public void delete(Integer bookID) {
		bookDao.delete(bookID);
	}
	
	private boolean validate(Book book) {
		return !StringUtils.isAnyBlank(book.getTitle(),book.getAuthor(),book.getISBN(),book.getSubject(), book.getstatus());
	}
	
	
}
