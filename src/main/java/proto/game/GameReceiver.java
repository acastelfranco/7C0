package proto.game;

import dao.GameEntry;
import dao.mysql.GameDao;
import proto.BaseReceiver;

public class GameReceiver extends BaseReceiver<GameEntry, String>
{
	
	
	public GameReceiver(String game) {
		super(new GameDao(game));
	}
	
	public int createEntry() {
		return ((GameDao) dao).save(null);
	}
}
