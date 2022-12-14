package command.game;

import command.BaseReceiver;
import dao.GameEntry;
import dao.mysql.GameDao;

public class GameReceiver extends BaseReceiver<GameEntry, String>
{
	private String game;
	private String username;
	
	public GameReceiver(String game, String username) {
		super(new GameDao(game));
		this.game = game;
		this.username = username;
	}
	
	public int createEntry() {
		return ((GameDao) dao).save(null);
	}
	
	public String getGame() {
		return game;
	}
	
	public String getUsername() {
		return username;
	}
}
