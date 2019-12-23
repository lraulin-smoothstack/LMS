package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> 
{
	public BorrowerDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	
	public Integer saveBorrowerWithId(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_borrower (name, address, phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	public void editBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_borrower SET name = ? WHERE cardNo = ?", new Object[] {borrower.getName(), borrower.getCardNo()});
		save("UPDATE tbl_borrower SET address = ? WHERE cardNo = ?", new Object[] {borrower.getAddress(), borrower.getCardNo()});
		save("UPDATE tbl_borrower SET phone = ? WHERE cardNo = ?", new Object[] {borrower.getPhone(), borrower.getCardNo()});
	}
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readBorrowers() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_borrower", null);
	}
	
	public List<Borrower> readBorrowersByName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_borrower WHERE name LIKE ?", new Object[] { searchString });
	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		BookLoansDAO bldao = new BookLoansDAO(conn);
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next())
		{
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setBookLoans(bldao.read("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[] {borrower.getCardNo()}));
			borrowers.add(borrower);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next())
		{
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
	
}
