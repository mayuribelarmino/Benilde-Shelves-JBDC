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
import com.benildeshelves.rest.book.domain.Lend1;
import com.benildeshelves.rest.book.service.BookService;
import com.benildeshelves.rest.book.service.BookServiceImpl;
import com.benildeshelves.rest.book.service.LendService;
import com.benildeshelves.rest.book.service.LendServiceImpl;

@Path("/lend")
public class LendController {

	
	private LendService lendService;

	public LendController() {
	
		this.lendService = new LendServiceImpl();
	}

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lend1> getLend(
			@QueryParam("lendID") Integer lendID, 
			@QueryParam("borrowerName") String borrowerName,
			@QueryParam("dateBorrowed") Date dateBorrowed, 
			@QueryParam("dateDue") Date dateDue,
			@QueryParam("dateReturned") Date dateReturned,
			@QueryParam("lendStatus") String lendStatus,
			@QueryParam("Title") String Title,
			@QueryParam("borrowerName") String borowerName)
	
			 {

		try {
			List<Lend1> lends1;
			
			if (StringUtils.isAllBlank(borrowerName)) {
				lends1 = lendService.findAll();
			} else {
				lends1 = lendService.findByName(borrowerName);
			}
						
			return lends1;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	
	@GET
	@Path("{lendID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lend getLend(@PathParam("lendID") String lendID) {

		try {
			int IntId1 = Integer.parseInt(lendID);
			Lend lend = lendService.find(IntId1);
			return lend;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLend(Lend lend) {
		System.out.println("lend");
		try {
			lendService.add(lend);
			String result = "Book saved : " + lend.getbookID()+ " " + lend.getborrowerName()+" "+lend.getdateBorrowed() +" " + lend.getdateDue() +" "+lend.getdateReturned();
			return Response.status(201).entity(result).build();
			
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		
		

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(Lend lend) {

		try {
			lendService.upsert(lend);
			String result = "Lend updated : " + lend.getbookID()+ " " + lend.getborrowerName()+" "+lend.getdateBorrowed() +" " + lend.getdateDue() +" "+lend.getdateReturned();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@DELETE
	@Path("{lendID}")
	public Response deleteUser(@PathParam("lendID") String lendID) {

		try {
			int IntId = Integer.parseInt(lendID);
			lendService.delete(IntId);
			String result = "Lend deleted";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
}

