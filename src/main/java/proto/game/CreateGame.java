package proto.game;

import proto.Command;

public class CreateGame implements Command
{
	private GameReceiver receiver;
	private int          retCode;

	public CreateGame(GameReceiver receiver) {
		this.receiver = receiver;
		this.retCode = 0;
	}

	@Override
	public void execute() {
		retCode = receiver.createEntry();
		
	}

	public int getRetCode() {
		return retCode;
	}

}
