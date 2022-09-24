package proto.board;

import dao.BoardEntry;
import proto.Command;

public class UpdateBoardEntry implements Command {
	private BoardReceiver receiver;
	private BoardEntry    entry;
	private int           retCode;
	
	public UpdateBoardEntry(BoardReceiver receiver, BoardEntry entry)
	{
		this.receiver = receiver;
		this.entry    = entry;
		this.retCode  = 0;
	}

	@Override
	public void execute()
	{
		receiver.updateEntry(entry);
	}

	public int getRetCode()
	{
		return retCode;
	}
}
