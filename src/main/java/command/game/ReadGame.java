package command.game;

import java.util.List;

import command.Command;
import dao.GameEntry;

public class ReadGame implements Command {
	
	private GameReceiver receiver;
	private List<GameEntry> game;
	
	public ReadGame(GameReceiver receiver)
	{
		this.receiver = receiver;
		this.game = null;
	}

	@Override
	public void execute() {
		game = receiver.readEntries();
	}
	
	public List<GameEntry> getGame() {
		return game;
	}
}
