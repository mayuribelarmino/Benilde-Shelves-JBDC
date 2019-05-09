package com.benildeshelves.rest.book.service;

import java.util.List;

import com.benildeshelves.rest.book.domain.Book;
import  com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Lend1;


public interface LendService {

public List<Lend1> findAll();
	
public Lend find(Integer lendID);
public List<Lend1> findByName(String borrowerName, String bookID);  

public void add(Lend lend);

public void upsert(Lend lend);

public void delete(Integer lendID);


}

