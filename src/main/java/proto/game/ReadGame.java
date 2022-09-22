package proto.game;

import java.util.List;

import dao.GameEntry;
import proto.Command;

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
