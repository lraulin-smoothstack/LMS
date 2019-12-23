package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> 
{
	public GenreDAO(Connection conn)
	{
		super(conn);
	}
	
	public void saveGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
	}
	
	public Integer saveGenreWithId(Genre genre) throws ClassNotFoundException, SQLException
	{
		return saveWithId("INSERT INTO tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
	}
	
	public void editGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public List<Genre> readGenres() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_genre", null);
	}
	
	public List<Genre> readGenresByGenreName(String searchString) throws ClassNotFoundException, SQLException
	{
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_genre WHERE genre_name LIKE ?", new Object[] { searchString });
	}
	
	public void insertBookGenre(Integer genreId, Integer bookId) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_genres (genre_id, bookId) values (?,?)", new Object[] {genreId, bookId });
	}
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next())
		{
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genre.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId IN "
					+ "(SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)", new Object[] {genre.getGenreId()}));
			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next())
		{
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}
	
}
