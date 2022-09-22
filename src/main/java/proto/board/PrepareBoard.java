package proto.board;

import java.util.List;

import dao.BoardEntry;
import proto.Command;

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
