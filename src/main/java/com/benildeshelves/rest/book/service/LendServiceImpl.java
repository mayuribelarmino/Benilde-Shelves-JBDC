package com.benildeshelves.rest.book.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.benildeshelves.rest.book.dao.LendDao;
import com.benildeshelves.rest.book.dao.LendJbdcDaoImpl;
import com.benildeshelves.rest.book.service.LendService;
import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Lend1;
import com.benildeshelves.rest.book.domain.Status;

public class LendServiceImpl implements LendService {
	
	LendDao lendDao;

	public LendServiceImpl() {
		this.lendDao = LendJbdcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}


	
	

	@Override
	public List<Lend1> findAll() {
		return lendDao.findAll();
	}

	@Override
	public Lend find(Integer lendID) {
		return lendDao.find(lendID);
	}

	@Override
	public List<Lend1> findByName(String borrowerName, String bookID) {
		return lendDao.findByName(borrowerName, bookID);
	}

	@Override
	public void add(Lend lend) {
		if (validate(lend)) {
			lendDao.add(lend);
		} else {
			throw new IllegalArgumentException("Fields bookID, borrowerName, dates cannot be null.");
		}
	}
	
	

	@Override
	public void upsert(Lend lend) {
		if (validate(lend)) {
			if(lend.getlendID() != null && lend.getlendID() >= 0) {
				lendDao.update(lend);
			} else {
				lendDao.add(lend);
			}
		} else {
			throw new IllegalArgumentException("Fields cannot be blank.");
		}
	}

	@Override
	public void delete(Integer lendID) {
		lendDao.delete(lendID);
	}
	
	private boolean validate(Lend lend) {
		return !StringUtils.isAnyBlank(lend.getborrowerName(), lend.getlendStatus());
	}
	
	

}
