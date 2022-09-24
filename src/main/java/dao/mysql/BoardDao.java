package dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import dao.BoardEntry;
import dao.Dao;
import servlets.Init;

public class BoardDao implements Dao<BoardEntry, String> {

	@Override
	public BoardEntry get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardEntry> getAll() {
		Statement statement;
		List<BoardEntry> entries = new Vector<BoardEntry>(); 
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return entries;
		}
		
		String query = "SELECT * FROM Board"; 
		
		try (ResultSet resultSet = statement.executeQuery(query)) {
			while(resultSet.next()) {
				BoardEntry entry = new BoardEntry();
				entry.setCountryId(resultSet.getString("country_id"));
				entry.setCountryName(resultSet.getString("country_name"));
				entry.setTanks(resultSet.getInt("tanks"));
				entry.setUsername(resultSet.getString("username"));
				entries.add(entry);
			}
		} catch (SQLException e1) {
			return entries;
		}
		
		return entries;
	}

	@Override
	/**
	 * We do not create any new entry in this table
	 */
	public int save(BoardEntry entry) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardEntry entry) {
		
		Statement statement;
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return -1;
		}
		
		String query = "UPDATE Board SET "
				+ "country_name = "
				+ "\""
				+ entry.getCountryName()
				+ "\""
				+ ", "
				+ "tanks = "
				+ "\""
				+ entry.getTanks()
				+ "\""
				+ ", "
				+ "username = "
				+ "\""
				+ entry.getUsername()
				+ "\""
				+ " "
				+ "WHERE country_id = "
				+ "\""
				+ entry.getCountryId()
				+ "\"";
		
		int count;
		
		try {
			count = statement.executeUpdate(query);
		} catch (SQLException e) {
			return -1;
		}
		
		return (count == 1) ? 0 : -1;
	}

	/**
	 * We do not delete any entry in this table
	 */
	@Override
	public int delete(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

}
