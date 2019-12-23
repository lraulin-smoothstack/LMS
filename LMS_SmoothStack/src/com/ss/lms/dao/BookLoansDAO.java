package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> 
{
	public BookLoansDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveBookLoans(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)", 
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate(), bl.getDateIn()});
	}
	
	public Integer saveBookLoansWithId(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)", 
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate(), bl.getDateIn()});
	}
	
	public void deleteBookLoans(BookLoans bl) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book_loans WHERE bookId = ? && branchId = ? && cardNo = ? &"
				+ "dateOut = ?", new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut()});
	}
	
	public List<BookLoans> readBookLoans() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_book_loans", null);
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bls = new ArrayList<BookLoans>();
		while(rs.next())
		{
			BookLoans bl = new BookLoans();
			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));
			bls.add(bl);
		}
		return bls;
	}

	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bls = new ArrayList<BookLoans>();
		while(rs.next())
		{
			BookLoans bl = new BookLoans();
			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));
			bls.add(bl);
		}
		return bls;
	}
	
}
