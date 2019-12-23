package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> 
{
	public static Connection conn;
	
	public BaseDAO(Connection conn)
	{
		this.conn = conn;
	}
	
	public void save(String sql, Object[] values) throws ClassNotFoundException, SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql);
		int count = 1;
		if(values != null)
		{
			for(Object o: values)
				stmt.setObject(count++, o);
		}
		stmt.executeUpdate();
	}
	
	public Integer saveWithId(String sql, Object[] values) throws ClassNotFoundException, SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		if(values != null)
		{
			for(Object o: values)
			{
				stmt.setObject(count++, o);
			}
		}
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		while(rs.next())
		{
			return rs.getInt(1);
		}
		return null;
	}
	
	public List<T> read(String sql, Object[] values) throws ClassNotFoundException, SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		if(values != null)
		{
			for(Object o: values)
			{
				stmt.setObject(count++, o);
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractData(rs);
	}
	
	public List<T> readFirstLevel(String sql, Object[] values) throws ClassNotFoundException, SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		if(values != null)
		{
			for(Object o: values)
			{
				stmt.setObject(count++, o);
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractDataFirstLevel(rs);
	}
	
	public abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
	public abstract List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException;
}
