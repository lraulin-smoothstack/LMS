package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.entity.Author;

public class AdminService 
{

	ConnectionUtil connUtil = new ConnectionUtil();

	public List<Author> readAuthors() throws SQLException {
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
}
