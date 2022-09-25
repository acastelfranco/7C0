package command.game;

import command.Command;
import dao.GameEntry;

public class ReadGameEntry implements Command {
	private GameReceiver receiver;
	private GameEntry entry;
	private String username;
	
	public ReadGameEntry(GameReceiver receiver, String username)
	{
		this.receiver = receiver;
		this.username = username;
		this.entry = null;
	}

	@Override
	public void execute() {
		entry = receiver.readEntry(username);
	}
	
	public GameEntry getEntry() {
		return entry;
	}
}
