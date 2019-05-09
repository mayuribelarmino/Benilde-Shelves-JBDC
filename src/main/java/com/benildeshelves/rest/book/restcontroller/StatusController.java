package com.benildeshelves.rest.book.restcontroller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.benildeshelves.rest.book.domain.Book;
import com.benildeshelves.rest.book.domain.Lend;
import com.benildeshelves.rest.book.domain.Status;
import com.benildeshelves.rest.book.service.BookService;
import com.benildeshelves.rest.book.service.BookServiceImpl;
import com.benildeshelves.rest.book.service.LendService;
import com.benildeshelves.rest.book.service.LendServiceImpl;
import com.benildeshelves.rest.book.service.StatusService;
import com.benildeshelves.rest.book.service.StatusServiceImpl;

@Path("/status")
public class StatusController {

	private StatusService statusService;


	public StatusController() {
		this.statusService = new StatusServiceImpl();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Status> getStatus(
			@QueryParam("statusID") Integer statusID, 
			@QueryParam("Availability") String Availability)
			 {

		try {
			List<Status> status;
			
			if (StringUtils.isAllBlank(Availability)) {
				status = statusService.findAll();
			} else {
				status = statusService.findByName(Availability);
			}
						
			return status;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	


	@GET
	@Path("{statusID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Status getStatus(@PathParam("statusID") String statusID) {

		try {
			int IntId = Integer.parseInt(statusID);
			Status status = statusService.find(IntId);
			return status;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	

	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(Book book) {

		try {
			bookService.add(book);
			String result = "Book saved : " + book.getTitle() + " " + book.getAuthor()+ book.getISBN() + " "+ book.getSubject() + " ";
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	//@PUT
	//@Consumes(MediaType.APPLICATION_JSON)
	//public Response updateUser(Book book) {

	/*	try {
			bookService.upsert(book);
			String result = "User updated : " + user.getFirstName() + " " + user.getLastName();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}*/

	/*@DELETE
	@Path("{bookID}")
	public Response deleteUser(@PathParam("bookID") String bookID) {

		try {
			int IntId = Integer.parseInt(bookID);
			bookService.delete(IntId);
			String result = "Book deleted";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}*/
}
