package proto.board;

import dao.BoardEntry;
import dao.mysql.BoardDao;
import proto.BaseReceiver;

public class BoardReceiver extends BaseReceiver<BoardEntry, String>
{
	public BoardReceiver() {
		super(new BoardDao());
	}
	
}
