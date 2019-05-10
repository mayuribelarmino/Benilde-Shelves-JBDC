package com.benildeshelves.rest.book.restcontroller;

import java.sql.Date;
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
import com.benildeshelves.rest.book.service.BookService;
import com.benildeshelves.rest.book.service.BookServiceImpl;
import com.benildeshelves.rest.book.service.LendService;
import com.benildeshelves.rest.book.service.LendServiceImpl;

@Path("/books")
public class BooksController {

	private BookService bookService;


	public BooksController() {
		this.bookService = new BookServiceImpl();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooks(
			@QueryParam("bookID") Integer bookID, 
			@QueryParam("Title") String Title, 
			@QueryParam("Author") String Author,
			@QueryParam("ISBN") String ISBN, 
			@QueryParam("Subject") String Subject,
			@QueryParam("status") String status,
			@QueryParam("datePublished") String datePublished,
			@QueryParam("dateAcquired") String dateAcquired) {
		System.out.println("enter");

		try {
			List<Book> books;
			
			if (StringUtils.isAllBlank(Title, Author, ISBN,Subject,status)) {
				books = bookService.findAll();
			} else {
				books = bookService.findByName(Title,Author,ISBN,Subject,status);
			}
			
			System.out.println("books");
			
			for(int i=0; i< books.size(); i++) {
				
				System.out.println(books.get(i).getbookID());
			}
			
			for (Book book : books) {
				System.out.println(book.getbookID());
			}
						
			return books;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	


	@GET
	@Path("{bookID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("bookID") String bookID) {

		try {
			int IntId = Integer.parseInt(bookID);
			Book book = bookService.find(IntId);
			return book;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	

	@POST
	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		System.out.println("add" );
		try {
			bookService.add(book);
			String result = "Book saved : " + book.getTitle() + " " + book.getAuthor()+ book.getISBN() + " "+ book.getSubject() + " "+ book.getdatePublished() + " "+ book.getdateAcquired() + " ";
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook(Book book) {

		try {
			bookService.upsert(book);
			String result = "User updated : " + book.getTitle() + " " + book.getAuthor() + " " + book.getISBN() + " " + book.getSubject() + " " + book.getdatePublished() + " " + book.getdateAcquired()+ " " + book.getstatus();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	@PUT
	@Path("edit/{bookID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook1(@PathParam("bookID") String bookID) {

		try {
			int IntId = Integer.parseInt(bookID);
			bookService.updatebook(IntId);
			String result = "Book updated";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Path("edit1/{bookID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook2(@PathParam("bookID") String bookID) {

		try {
			int IntId = Integer.parseInt(bookID);
			bookService.updatebook1(IntId);
			String result = "Book updated";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@DELETE
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
	}
}
