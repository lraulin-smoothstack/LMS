package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Author;

public class AuthorDAO extends BaseDAO<Author> 
{
	public AuthorDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public Integer saveAuthorWithId(Author author) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAuthors() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_author", null);
	}
	
	public List<Author> readAuthorsByAuthorName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] { searchString });
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		List<Author> authors = new ArrayList<Author>();
		while(rs.next())
		{
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			author.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId IN "
					+ "(SELECT bookId FROM tbl_book_authors WHERE authorId = ?)", new Object[] {author.getAuthorId()}));
			authors.add(author);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Author> authors = new ArrayList<Author>();
		while(rs.next())
		{
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
	
}
