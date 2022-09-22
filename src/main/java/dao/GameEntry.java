package dao;

import java.io.Serializable;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class GameEntry implements Serializable, Entry
{
	private static final long serialVersionUID = 3388594243491715546L;
	private String username;
	private String color;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public void fromJSON(JSONObject json) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JSONObject toJSON() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
