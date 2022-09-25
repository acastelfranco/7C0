package dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.Dao;
import dao.UserEntry;
import servlet.Init;

public class UserDao implements Dao<UserEntry, String> {

	@Override
	public UserEntry get(String username) {

		Statement statement;
		UserEntry user = new UserEntry(); 
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return user;
		}
		
		String query = "SELECT * FROM User WHERE username ='" + username + "'"; 
		
		try (ResultSet resultSet = statement.executeQuery(query)) {
			if(resultSet.next()) {
				user.setUsername(resultSet.getString("username")); 
				user.setPassword(resultSet.getString("password")); 
				user.setName(resultSet.getString("name")); 
				user.setSurname(resultSet.getString("surname"));
				user.setGame(resultSet.getString("game"));
				user.setColor(resultSet.getString("color"));
			}
		} catch (SQLException e1) {
			return user;
		}
		
		return user;
	}

	@Override
	public List<UserEntry> getAll() {
		return null;
	}

	@Override
	public int save(UserEntry user) {
		Statement statement;
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return -1;
		}
		
		String query = "INSERT INTO User VALUES ("
				+ "\""
				+ user.getUsername()
				+ "\""
				+ ", \""
				+ user.getPassword()
				+ "\""
				+ ", \""
				+ user.getName()
				+ "\""
				+ ", \""
				+ user.getSurname()
				+ "\""
				+ ", \""
				+ user.getGame()
				+ "\""
				+ ", \""
				+ user.getColor()
				+ "\")";
		
		int count;
		
		try {
			count = statement.executeUpdate(query);
		} catch (SQLException e) {
			return -1;
		}
		
		return (count == 1) ? 0 : -1;
		
	}

	@Override
	public int update(UserEntry user) {

		Statement statement;
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return -1;
		}
		
		String query = "UPDATE User SET password = " + "\"" + user.getPassword() + "\""
				+ ","
				+ "name = "
				+ "\""
				+ user.getName()
				+ "\""
				+ ","
				+ "surname = "
				+ "\""
				+ user.getSurname()
				+ "\""
				+ ","
				+ "game = "
				+ "\""
				+ user.getGame()
				+ "\""
				+ " "
				+ "WHERE username = "
				+ "\""
				+ user.getUsername()
				+ "\"";
		
		int count;
		
		try {
			count = statement.executeUpdate(query);
		} catch (SQLException e) {
			return -1;
		}
		
		return (count == 1) ? 0 : -1;
	}

	@Override
	public int delete(String username) {
		
		Statement statement;
		
		try {
			statement = Init.connection.createStatement();
		} catch (SQLException e1) {
			return -1;
		}
		
		String query = "DELETE FROM User WHERE username = \"" + username + "\"";
		
		int count;
		
		try {
			count = statement.executeUpdate(query);
		} catch (SQLException e) {
			return -1;
		}
		
		return (count == 1) ? 0 : -1;
	}

}
