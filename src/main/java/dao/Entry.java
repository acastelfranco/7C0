package dao;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface Entry {
	public void fromJSON(JSONObject json);
	
	public JSONObject toJSON() throws ParseException;
}
