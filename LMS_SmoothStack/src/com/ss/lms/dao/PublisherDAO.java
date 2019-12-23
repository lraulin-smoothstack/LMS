package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	public PublisherDAO(Connection conn)
	{
		super(conn);
	}
	
	public void savePublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		save("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)",
				new Object[] {p.getPublisherName(),p.getPublisherAddress(),p.getPublisherPhone()});
	}
	public Integer savePublisherWithId(Publisher p) throws SQLException, ClassNotFoundException
	{
		return saveWithId("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)",
				new Object[] {p.getPublisherName(),p.getPublisherAddress(),p.getPublisherPhone()});
	}
	public void editPublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		save("UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?", new Object[] {p.getPublisherName(),p.getPublisherId()});
		save("UPDATE tbl_publisher SET publisherAddress = ? WHERE publisherId = ?", new Object[] {p.getPublisherAddress(),p.getPublisherId()});
		save("UPDATE tbl_publisher SET publisherPhone = ? WHERE publisherId = ?", new Object[] {p.getPublisherPhone(),p.getPublisherId()});
	}
	public void deletePublisher(Publisher p) throws SQLException, ClassNotFoundException
	{
		save( "DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] {p.getPublisherId()});
	}
	
	public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException
	{
		return read("SELECT * FROM tbl_publishers", null);
	}
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> ps = new ArrayList<Publisher>();
		while(rs.next())
		{
			Publisher pub = new Publisher();
			pub.setPublisherId(rs.getInt("publisherId"));
			pub.setPublisherName(rs.getString("publisherName"));
			pub.setPublisherAddress(rs.getString("publisherAddress"));
			pub.setPublisherPhone(rs.getString("publisherPhone"));
			ps.add(pub);
		}
		return ps;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> ps = new ArrayList<Publisher>();
		while(rs.next())
		{
			Publisher pub = new Publisher();
			pub.setPublisherId(rs.getInt("publisherId"));
			pub.setPublisherName(rs.getString("publisherName"));
			pub.setPublisherAddress(rs.getString("publisherAddress"));
			pub.setPublisherPhone(rs.getString("publisherPhone"));
			ps.add(pub);
		}
		return ps;
	}

}
