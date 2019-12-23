package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> 
{
	public BookCopiesDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveBookCopies(BookCopies bc) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) values (?)", new Object[] {bc.getBookId(),bc.getBranchId(),bc.getNoOfCopies()});
	}
	
	public Integer saveBookCopiesWithId(BookCopies bc) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) values (?)", new Object[] {bc.getBookId(),bc.getBranchId(),bc.getNoOfCopies()});
	}
	
	public void editBookCopies(BookCopies bc) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? && branchId = ?", new Object[] {bc.getNoOfCopies(), bc.getBookId(), bc.getBranchId()});
	}
	public void deleteBookCopies(BookCopies bc) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book_copies WHERE bookId = ? && branchId = ?", new Object[] {bc.getBookId(), bc.getBranchId()});
	}
	
	public List<BookCopies> readBookCopies() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_book_copies", null);
	}
	
	
	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		
		List<BookCopies> bcs = new ArrayList<BookCopies>();
		while(rs.next())
		{
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			bcs.add(bc);
		}
		return bcs;
	}

	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bcs = new ArrayList<BookCopies>();
		while(rs.next())
		{
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			bcs.add(bc);
		}
		return bcs;
	}
	
}
