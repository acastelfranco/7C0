package proto.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import dao.BoardEntry;
import jakarta.servlet.http.HttpServletResponse;
import proto.Invoker;

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
	
	public static void prepareBoard(HttpServletResponse response) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		List<BoardEntry> entries = readBoard();
		
		List<Integer> indexes = generateRandomIndexes(entries.size());
		
		writer.append("[");
		
		for (int i = 0; i < entries.size(); i++) {
			writer.append(entries.get(indexes.get(i)).toJSON().toJSONString());
			
			if (i < entries.size() - 1)
				writer.append(",");
		}
		
		writer.append("]");
		
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}
}
