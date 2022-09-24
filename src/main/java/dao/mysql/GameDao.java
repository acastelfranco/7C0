package dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import dao.BoardEntry;
import dao.Dao;
import dao.GameEntry;
import servlets.Init;

public class GameDao implements Dao<GameEntry, String> {
	
	private String viewName;
	
	public GameDao(String viewName)
	{
		this.viewName = viewName;
	}

	@Override
	public GameEntry get(String username) {
		Statement statement;
		GameEntry entry = new GameEntry(); 
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return entry;
		}
		
		String query = "SELECT * FROM " + viewName.replaceAll(" ", "_") + " WHERE username = '" + username + "'"; 
		
		try (ResultSet resultSet = statement.executeQuery(query)) {
			if(resultSet.next()) {
				entry.setUsername(resultSet.getString("username"));
				entry.setColor(resultSet.getString("color"));
			}
		} catch (SQLException e1) {
			return entry;
		}
		
		return entry;
	}

	@Override
	public List<GameEntry> getAll() {
		Statement statement;
		List<GameEntry> entries = new Vector<GameEntry>(); 
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return entries;
		}
		
		String query = "SELECT * FROM " + viewName.replaceAll(" ", "_"); 
		
		try (ResultSet resultSet = statement.executeQuery(query)) {
			while(resultSet.next()) {
				GameEntry entry = new GameEntry();
				entry.setUsername(resultSet.getString("username"));
				entry.setColor(resultSet.getString("color"));
				entries.add(entry);
			}
		} catch (SQLException e1) {
			return entries;
		}
		
		return entries;
	}
	
	@Override
	public int update(GameEntry t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(GameEntry t) {
		Statement statement;
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return -1;
		}
		
		String query = "CREATE VIEW " + viewName.replaceAll(" ", "_") + " AS "
				+ "SELECT username, color "
				+ "FROM User "
				+ "WHERE game = '" + viewName + "'";
		
		int count;
		
		try {
			count = statement.executeUpdate(query);
		} catch (SQLException e) {
			return -1;
		}
		
		return count;
	}

}
