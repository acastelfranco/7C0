package proto.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.parser.ParseException;

import dao.GameEntry;
import jakarta.servlet.http.HttpServletResponse;
import proto.Invoker;
import servlets.Utils;

public class GameClient
{
	public static void readGame(HttpServletResponse response, String gameName) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		GameReceiver receiver = new GameReceiver(gameName);
		ReadGame command = new ReadGame(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		List<GameEntry> entries = command.getGame();
		
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

	public static void createGame(HttpServletResponse response, String gameName) throws IOException
	{
		GameReceiver receiver = new GameReceiver(gameName);
		CreateGame command = new CreateGame(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		int result = command.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to create the game: " + gameName);
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
}