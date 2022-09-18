package dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Entity
@Table(name="User")
public class User implements Serializable
{
	private static final long serialVersionUID = 6048398966708251829L;
	
	@Id
	private String username;
	private String password;
	private String name;
	private String surname;
	
	public User() {
		username = "null";
		password = "null";
		name = "null";
		surname = "null";
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void fromJSON(JSONObject json) {
		username = (String) json.get("username");
		password = (String) json.get("password");
		name     = (String) json.get("name");
		surname  = (String) json.get("surname");
	}
	
	public JSONObject toJSON() throws ParseException {
		JSONParser parser = new JSONParser();
		
		JSONObject json = (JSONObject) parser.parse("{\r\n"
		    	 + "	\"username\": \"" + username + "\",\r\n"
		    	 + "	\"password\": \"" + password + "\",\r\n"
		    	 + "	\"name\": \""     + name     + "\",\r\n"
		    	 + "	\"surname\": \""  + surname  + "\"\r\n"
		    	 + "}");
		
		return json;
	}
}
