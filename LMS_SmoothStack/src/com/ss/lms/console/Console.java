package com.ss.lms.console;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.entity.Author;
import com.ss.lms.services.AdminService;

public class Console 
{
	AdminService adminService = new AdminService();
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Console con = new Console();
		con.readAuthors();
	}
	
	public List<Author> readAuthors() throws SQLException{
		List<Author> authors = adminService.readAuthors();
		return authors;
	}
}
