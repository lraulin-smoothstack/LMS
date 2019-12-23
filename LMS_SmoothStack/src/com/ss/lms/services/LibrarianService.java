package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;

public class LibrarianService 
{
	ConnectionUtil connUtil = new ConnectionUtil();
	
	/*
	 * READ METHODS:
	 */
	public List<Author> readAuthors() throws SQLException 
	{
		Connection conn = null;
		List<Author> authors = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			authors = adao.readAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return authors;
	}
	
	public List<Book> readBooks() throws SQLException 
	{
		Connection conn = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			books = bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return books;
	}
	
	public List<BookCopies> readBookCopies() throws SQLException 
	{
		Connection conn = null;
		List<BookCopies> bcs = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcs = bcdao.readBookCopies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bcs;
	}
	
	public List<BookLoans> readBookLoans() throws SQLException 
	{
		Connection conn = null;
		List<BookLoans> bls = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bls = bldao.readBookLoans();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bls;
	}
	
	public List<Borrower> readBorrowers() throws SQLException
	{
		Connection conn = null;
		List<Borrower> borrowers = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			borrowers = bdao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return borrowers;
	}
	
	public List<Branch> readBranches() throws SQLException 
	{
		Connection conn = null;
		List<Branch> branches = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			branches = bdao.readBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return branches;
	}
	
	public List<Genre> readGenres() throws SQLException
	{
		Connection conn = null;
		List<Genre> genres = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			genres = gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading genres faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return genres;
	}
	/*
	 * EDIT:
	 */
	public String editBranch(Branch b) throws SQLException
	{
		Connection conn = null;
		try
		{
			conn = connUtil.getConnection();
			BranchDAO dao = new BranchDAO(conn);
			dao.editBranch(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Branch Failed");
			if(conn != null) {
				conn.rollback();
			}
		} finally {
			if(conn!= null)
				conn.close();
		}
		return "Branch Edit Success";
	}

	public String editBookCopies(BookCopies bc) throws SQLException
	{
		Connection conn = null;
		try
		{
			conn = connUtil.getConnection();
			BookCopiesDAO dao = new BookCopiesDAO(conn);
			dao.editBookCopies(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Copies Failed");
			if(conn != null) {
				conn.rollback();
			}
		} finally {
			if(conn!= null)
				conn.close();
		}
		return "Book Copies Edit Success";
	}
	/*
	 * CONSOLE METHODS:
	 */
	public List<BookCopies> readBookCopiesFromBranch(Branch branch) throws SQLException
	{
		Connection conn = null;
		List<BookCopies> bcs = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcs = bcdao.readBookCopiesFromBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bcs;
	}

}
