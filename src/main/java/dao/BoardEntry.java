package dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Entity
@Table(name="Board")
public class BoardEntry  implements Serializable, Entry
{
	private static final long serialVersionUID = -7941607667403660965L;

	@Id
	private String countryId;
	private String countryName;
	private int tanks;
	private String username;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public int getTanks() {
		return tanks;
	}
	
	public void setTanks(int tanks) {
		this.tanks = tanks;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void fromJSON(JSONObject json) {
		countryId = (String) json.get("countryId");
		countryName = (String) json.get("countryName");
		tanks     = Integer.parseInt((String) json.get("tanks"));
		username  = (String) json.get("username");
	}
	
	public JSONObject toJSON() throws ParseException {
		JSONParser parser = new JSONParser();
		
		JSONObject json = (JSONObject) parser.parse("{\r\n"
		    	 + "	\"countryId\": \"" + countryId + "\",\r\n"
		    	 + "	\"countryName\": \"" + countryName + "\",\r\n"
		    	 + "	\"tanks\": \""     + tanks     + "\",\r\n"
		    	 + "	\"username\": \""  + username  + "\"\r\n"
		    	 + "}");
		
		return json;
	}
}
