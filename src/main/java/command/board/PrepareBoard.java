package command.board;

import java.util.List;

import command.Command;
import dao.BoardEntry;

public class PrepareBoard implements Command
{
	private BoardReceiver    receiver;
	private List<BoardEntry> board;
	
	public PrepareBoard(BoardReceiver receiver)
	{
		this.receiver = receiver;
		this.board = null;
	}

	@Override
	public void execute()
	{
		board = receiver.readEntries();
	}

	public List<BoardEntry> getBoard() {
		return board;
	}
}
