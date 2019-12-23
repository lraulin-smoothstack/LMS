package com.ss.lms.console;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.entity.*;
import com.ss.lms.services.*;

public class Console 
{
	AdminService adminService = new AdminService();
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Console con = new Console();
		List<Author> authors = con.readAuthors();
		List<Book> books = con.readBooks();
		List<Genre> genres = con.readGenres();
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
	
	
}
