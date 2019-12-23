package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch> 
{
	public BranchDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_library_branch (branchName, branchAddress) values (?,?)", new Object[] {branch.getBranchName(), branch.getAddress()});
	}
	
	public Integer saveBranchWithId(Branch branch) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_library_branch (branchName, branchAddress) values (?)", new Object[] {branch.getBranchName(),  branch.getAddress()});
	}
	public void editBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_branch SET branchName = ? WHERE branchId = ?", new Object[] {branch.getBranchName(), branch.getBranchId()});
		save("UPDATE tbl_branch SET branchAddress = ? WHERE branchId = ?", new Object[] {branch.getAddress(), branch.getBranchId()});
		
	}
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] {branch.getBranchId()});
	}
	
	public List<Branch> readBranches() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_library_branch", null);
	}
	
	public List<Branch> readBranchesByBranchName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_library_branch WHERE branchName LIKE ?", new Object[] { searchString });
	}
	
	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		BookLoansDAO bldao = new BookLoansDAO(conn);
		List<Branch> branches = new ArrayList<Branch>();
		while(rs.next())
		{
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBookCopies(bcdao.readFirstLevel("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[] {branch.getBranchId()}));
			branch.setBookLoans(bldao.readFirstLevel("SELECT * FROM tbl_book_loans WHERE branchId = ?", new Object[] {branch.getBranchId()}));
			branches.add(branch);
		}
		return branches;
	}

	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Branch> branches = new ArrayList<Branch>();
		while(rs.next())
		{
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branches.add(branch);
		}
		return branches;
	}
	
}
