package com.benildeshelves.rest.book.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.benildeshelves.rest.book.dao.BookDao;
import com.benildeshelves.rest.book.dao.BookJbdcDaoImpl;
import com.benildeshelves.rest.book.dao.StatusDao;
import com.benildeshelves.rest.book.dao.StatusJbdcDaoImpl;
import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Status;

public class StatusServiceImpl implements StatusService {
	StatusDao statusDao;

	public StatusServiceImpl() {
		this.statusDao = StatusJbdcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}
	
	@Override
	public List<Status> findAll() {
		return statusDao.findAll();
	}
	
	@Override
	public List<Status> findByName(String Availability) {
		return statusDao.findByName(Availability);
	}
	
	@Override
	public void add(Status status) {
		if (validate(status)) {
			statusDao.add(status);
		} else {
			throw new IllegalArgumentException("Status cannot be null.");
		}
	}
	private boolean validate(Status status) {
		return !StringUtils.isAnyBlank(status.getAvailability());
	}
	@Override
	public Status find(Integer statusID) {
		return statusDao.find(statusID);
	}

	
}
