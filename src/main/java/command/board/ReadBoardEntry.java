package command.board;

import command.Command;
import dao.BoardEntry;

public class ReadBoardEntry implements Command {
	private BoardReceiver receiver;
	private String        countryId;
	private BoardEntry    entry;
	
	public ReadBoardEntry(BoardReceiver receiver, String countryId)
	{
		this.receiver = receiver;
		this.countryId = countryId;
		this.entry = null;
	}

	@Override
	public void execute()
	{
		entry = receiver.readEntry(countryId);
	}

	public BoardEntry getEntry() {
		return entry;
	}
}
