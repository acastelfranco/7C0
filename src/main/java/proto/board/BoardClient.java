package proto.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import dao.BoardEntry;
import dao.GameEntry;
import jakarta.servlet.http.HttpServletResponse;
import proto.Invoker;
import proto.game.GameClient;
import proto.game.ReadGame;
import servlet.Utils;

public class BoardClient
{
	private static List<Integer> generateRandomIndexes(int size) {
		Random random = new Random();
		List<Integer> list = new Vector<Integer>(0);
		
		while (list.size() < size) {
			int current = random.nextInt(size);
			
			if (list.indexOf(current) == -1)
				list.add(current);
		}
		
		return list;
	}
	
	public static BoardEntry readBoardEntry(String countryId)
	{
		BoardReceiver receiver = new BoardReceiver();
		ReadBoardEntry read = new ReadBoardEntry(receiver, countryId);
		Invoker invoker = new Invoker(read);
		invoker.execute();
		return read.getEntry();
	}

	public static void readBoardEntry(HttpServletResponse response, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		BoardEntry entry = readBoardEntry(username);
		writer.append(entry.toJSON().toJSONString());
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}
	
	public static int updateBoardEntry(BoardEntry entry)
	{
		BoardReceiver receiver = new BoardReceiver();
		UpdateBoardEntry command = new UpdateBoardEntry(receiver, entry);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		return command.getRetCode();
	}
	
	private static void reportTanksAndColor(HttpServletResponse response, int tanks, String color) throws IOException
	{
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.append("{");
		writer.append(" ");
		writer.append("\"tanks\" : \"" + tanks + "\"");
		writer.append(",");
		writer.append(" ");
		writer.append("\"color\" : \"" + color + "\"");
		writer.append(" ");
		writer.append("}");
		writer.flush();
		writer.close();
	}
	
	public static void updateBoardEntry(HttpServletResponse response, String countryId, String username, String game) throws IOException
	{
		BoardEntry boardEntry = readBoardEntry(countryId);
		
		if (boardEntry == null) {
			Utils.reportError(response, 500, "Unable to read the board entry for the country id: " + countryId);
			return;
		}
		
		GameEntry gameEntry = GameClient.readGameEntry(game, boardEntry.getUsername());
		if (gameEntry.getUsername() == null || gameEntry.getColor() == null) {
			Utils.reportError(response, 500, "Unable to read the game entry for the user: " + username);
			return;
		}
		
		int tanks = boardEntry.getTanks() + 1;
		
		/* TODO: for now we are limiting the number of tanks per country to 5. In the final version it will be 130 */
		if (tanks > 5) {
			reportTanksAndColor(response, 5, gameEntry.getColor());
			return;
		}
		
		/* TODO: For now we do not touch the username. We will update it when we will get to the attacking and defending phases */
		boardEntry.setUsername(boardEntry.getUsername());
		boardEntry.setTanks(tanks);
		
		int result = updateBoardEntry(boardEntry);
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to update the country: " + boardEntry.getCountryName());
			return;
		}
		
		reportTanksAndColor(response, tanks, gameEntry.getColor());
	}
	
	public static int updateBoard(List<BoardEntry> board)
	{
		for (BoardEntry entry : board) {
			int retCode = updateBoardEntry(entry);
			if (retCode != 0) return retCode;
		}
		
		return 0;
	}
	
	public static List<BoardEntry> readBoard()
	{
		BoardReceiver receiver = new BoardReceiver();
		PrepareBoard command = new PrepareBoard(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		return command.getBoard();
	}
	
	public static void readBoard(HttpServletResponse response) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		List<BoardEntry> entries = readBoard();
		
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
	
	public static List<BoardEntry> prepareBoard(String gameName, String username)
	{
		List<GameEntry>  game = GameClient.readGame(gameName, username);
		List<BoardEntry> entries = readBoard();
		
		List<Integer> indexes = generateRandomIndexes(entries.size());
		
		List<BoardEntry> flushed = new Vector<BoardEntry>(0);
		Collections.reverse(game);
		
		for (int i = 0, j = 0; i < entries.size(); i++) {
			BoardEntry entry = entries.get(indexes.get(i));
			entry.setTanks(1);
			entry.setColor(game.get(j).getColor());
			entry.setUsername(game.get(j).getUsername());
			flushed.add(entry);
			j++;
			j %= game.size();
		}
		
		return flushed;
	}
	
	public static void prepareBoard(HttpServletResponse response, String gameName, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		List<BoardEntry> entries = prepareBoard(gameName, username);

		int result = updateBoard(entries);
		
		if (result == 0) {
			writer.append("{");
			writer.append("\"board\" : ");
			writer.append("[");
			
			for (int i = 0; i < entries.size(); i++) {
				writer.append(entries.get(i).toJSON().toJSONString());
				
				if (i < entries.size() - 1)
					writer.append(",");
			}
			
			writer.append("]");
			writer.append("}");

			response.setContentType("application/json");
		} else {
			Utils.reportError(response, 500, "Unable to update the game board");
			return;
		}
		
		writer.flush();
		writer.close();
	}
}
