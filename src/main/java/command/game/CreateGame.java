package command.game;

import java.util.List;

import command.Command;
import command.Invoker;
import command.user.UserClient;
import dao.GameEntry;
import dao.UserEntry;

public class CreateGame implements Command
{
	private GameReceiver receiver;
	private int          retCode;

	public CreateGame(GameReceiver receiver)
	{
		this.receiver = receiver;
		this.retCode = 0;
	}
	
	private boolean gameExists()
	{
		ReadGame command = new ReadGame(receiver);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		List<GameEntry> result = command.getGame();
		
		return (result.size() == 4);
	}

	private int setupPlayers(String game, String human)
	{
		UserEntry bot1 = UserClient.readUser("bot1");
		UserEntry bot2 = UserClient.readUser("bot2");
		UserEntry bot3 = UserClient.readUser("bot3");
		UserEntry humanPlayer = UserClient.readUser(human);
		
		bot1.setGame(game);
		bot2.setGame(game);
		bot3.setGame(game);
		humanPlayer.setGame(game);
		
		int ret = 0;
		
		if (ret == 0) ret = UserClient.updateUser(bot1);
		if (ret == 0) ret = UserClient.updateUser(bot2);
		if (ret == 0) ret = UserClient.updateUser(bot3);
		if (ret == 0) ret = UserClient.updateUser(humanPlayer);
		
		return ret;
	}

	@Override
	public void execute() {
		if (!gameExists()) {
			if (retCode == 0) retCode = setupPlayers(receiver.getGame(), receiver.getUsername());
			if (retCode == 0) retCode = receiver.createEntry();
		}
	}

	public int getRetCode() {
		return retCode;
	}

}
