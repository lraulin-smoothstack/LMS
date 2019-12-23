package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> 
{
	public BookDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveBook(Book book) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPubId()});
	}
	
	public Integer saveBookWithId(Book book) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPubId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readBooks() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> readBooksByTitle(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] { searchString });
	}
	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		AuthorDAO adao = new AuthorDAO(conn);
		List<Book> books = new ArrayList<Book>();
		while(rs.next())
		{
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubId(rs.getInt("pubId"));
			book.setAuthors(adao.readFirstLevel("SELECT * FROM tbl_author WHERE Id IN "
					+ "(SELECT authorId FROM tbl_book_authors WHERE bookId = ?", new Object[] {book.getBookId()}));
			books.add(book);
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next())
		{
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubId(rs.getInt("pubId"));
			books.add(book);
		}
		return books;
	}
	
}
