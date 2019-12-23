package com.ss.lms.console;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.entity.*;
import com.ss.lms.services.*;

public class Console 
{
	AdminService adminService = new AdminService();
	/*
	 * TODO: TEST ADD, EDIT, DELETE!
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Console con = new Console();
		
		//List<Author> authors = con.readAuthors();
		//authors.stream().forEach(author -> System.out.println(author.getAuthorName()));
		/*
		List<Book> books = con.readBooks();
		books.stream().forEach(o -> System.out.println(o.getTitle()));
		List<BookCopies> bcs = con.readBookCopies();
		bcs.stream().forEach(bc -> System.out.println( "bookId = " + bc.getBookId() + " branchId = " + bc.getBranchId() + " noOfCopies: " + bc.getNoOfCopies()));
		List<BookLoans> bls = con.readBookLoans();
		bls.stream().forEach(bl -> System.out.println("bookId = " + bl.getBookId() + " branchId = " + bl.getBranchId() + " cardNo = " + bl.getCardNo()));
		*/
		//List<Borrower> borrs = con.readBorrowers();
		//borrs.stream().forEach(b -> System.out.println(b.getCardNo() + ":" + b.getName()));
		//List<Branch> lbs = con.readBranches();
		//lbs.stream().forEach(b -> System.out.println (b.getBranchId() + ": " + b.getBranchName()));
		//List<Genre> genres = con.readGenres();
		//genres.stream().forEach(b -> System.out.println(b.getGenreId() + ":" + b.getGenreName()));
		
		
		
		
	}
	
	public List<Author> readAuthors() throws SQLException{
		List<Author> authors = adminService.readAuthors();
		return authors;
	}
	public List<Book> readBooks() throws SQLException{
		List<Book> books = adminService.readBooks();
		return books;
	}
	public List<Genre> readGenres() throws SQLException
	{
		List<Genre> genres = adminService.readGenres();
		return genres;
	}
	public List<BookCopies> readBookCopies() throws SQLException
	{
		return adminService.readBookCopies();
	}
	public List<BookLoans> readBookLoans() throws SQLException
	{
		return adminService.readBookLoans();
	}
	public List<Borrower> readBorrowers() throws SQLException
	{
		return adminService.readBorrowers();
	}
	public List<Branch> readBranches() throws SQLException
	{
		return adminService.readBranches();
	}
	
	
}
