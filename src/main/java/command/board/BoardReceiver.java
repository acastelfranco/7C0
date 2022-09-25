package command.board;

import command.BaseReceiver;
import dao.BoardEntry;
import dao.mysql.BoardDao;

public class BoardReceiver extends BaseReceiver<BoardEntry, String>
{
	public BoardReceiver() {
		super(new BoardDao());
	}
	
}
