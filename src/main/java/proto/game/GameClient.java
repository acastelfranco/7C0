package proto.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.parser.ParseException;

import dao.GameEntry;
import jakarta.servlet.http.HttpServletResponse;
import proto.Invoker;
import servlet.Utils;

public class GameClient
{
	public static GameEntry readGameEntry(String gameName, String username)
	{
		GameReceiver receiver = new GameReceiver(gameName, username);
		ReadGameEntry command = new ReadGameEntry(receiver, username);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		return command.getEntry();
	}
	
	public static List<GameEntry> readGame(String gameName, String username)
	{
		GameReceiver receiver = new GameReceiver(gameName, username);
		ReadGame command = new ReadGame(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		return command.getGame();
	}
	
	public static void readGame(HttpServletResponse response, String gameName, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		List<GameEntry> entries = readGame(gameName, username);
		
		writer.append("[");
		
		for (int i = 0; i < entries.size(); i++) {
			writer.append(entries.get(i).toJSON().toJSONString());
			
			if (i < entries.size() - 1)
				writer.append(",");
		}
		
		writer.append("]");
		
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}
	
	public static int createGame(String gameName, String username)
	{
		GameReceiver receiver = new GameReceiver(gameName, username);
		CreateGame command = new CreateGame(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		return command.getRetCode();
	}

	public static void createGame(HttpServletResponse response, String gameName, String username) throws IOException
	{
		int result = createGame(gameName, username);
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to create the game: " + gameName);
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
}
